package com.pms.controller;


import java.util.concurrent.ExecutorService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.pms.entity.Patient;
import com.pms.service.PatientService;
import com.pms.entity.UserDtls;
import com.pms.service.UserService;
import com.pms.entity.Report;
import com.pms.service.ReportService;


@Controller
public class PatientController {
    private PatientService patientService;
    private ReportService reportService;

    public PatientController(PatientService patientService, ReportService reportService) {
        super();
        this.patientService = patientService;
        this.reportService = reportService;
    }
    
    @Autowired
	private UserService userService;

	 @GetMapping("/")
	    public String signin() {
	        return "login";
	    }
    
	 
    @GetMapping("/home")
    public String home() {
        return "index";
    }
    
    @GetMapping("/signin")
   	public String login() {
   		return "login";
   	}

   	@GetMapping("/register")
   	public String register() {
   		return "register";
   	}
   	
   	@PostMapping("/createUser")
   	public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

   		// System.out.println(user);

   		boolean f = userService.checkEmail(user.getEmail());

   		if (f) {
   			session.setAttribute("msg", "Email Id alreday exists");
   		}

   		else {
   			UserDtls userDtls = userService.createUser(user);
   			if (userDtls != null) {
   				session.setAttribute("msg", "Register Sucessfully");
   			} else {
   				session.setAttribute("msg", "Something wrong on server");
   			}
   		}

   		return "redirect:/signin";
   	}


    @GetMapping({"/patients", "/list-patients"})
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "views/list_patients";
    }

    @GetMapping("/add")
    public String createPatient(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "views/create_patient";
    }

    @PostMapping("/processform")
    public String createPatientForm(@ModelAttribute Patient patient) {
    	Patient savedPatient = patientService.savePatients(patient);
        return "redirect:/add-report/" + savedPatient.getId();
    }
    
    
    
    @GetMapping("/add-report/{id}")
    public String createReportForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        Report report = new Report();
        report.setPatient(patient);
        model.addAttribute("report", report);
        model.addAttribute("firstName", patient.getFirstName()); // Add first name to the model
        model.addAttribute("lastName", patient.getLastName()); // Add last name to the model
        return "views/create_report";
    }


    @PostMapping("/process-report/{id}")
    public String createReport(@PathVariable Long id, @ModelAttribute Report report) {
        Patient patient = patientService.getPatientById(id);

    
        report.setPatient(patient);
        reportService.saveReport(report);
        return "redirect:/patients";
    }

    @GetMapping("/patient/update/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "views/update_patient";
    }

    @PostMapping("/updateform/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient, Model model) {
        Patient existingPatient = patientService.getPatientById(id);
        existingPatient.setId(id);
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setGender(patient.getGender());
        existingPatient.setCity(patient.getCity());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setMobile(patient.getMobile());
        existingPatient.setMedicalHistory(patient.getMedicalHistory());
        existingPatient.setFeesPaid(patient.getFeesPaid());

        patientService.updatePatients(existingPatient);
        System.out.println(existingPatient);
        return "redirect:/patients";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatients(id);
        return "redirect:/patients";
    }
    
    @GetMapping("/patient/view-report/{id}")
    public String viewReport(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        List<Report> reports = reportService.getReportsByPatient(patient);
        model.addAttribute("patient", patient);
        model.addAttribute("reports", reports);
        return "views/view_report";
    }
    
    @GetMapping("/search")
    public String searchPatients(@RequestParam(required = false) String keyword, Model model) {
        List<Patient> searchResults = patientService.searchPatients(keyword);
        model.addAttribute("patients", searchResults);
        return "views/list_patients";
    }
    @GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/signin";
	}



    
    
}

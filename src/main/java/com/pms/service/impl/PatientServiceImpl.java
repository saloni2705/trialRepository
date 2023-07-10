package com.pms.service.impl;

import java.util.List;


import java.util.ArrayList;


import org.springframework.stereotype.Service;

import com.pms.entity.Report;
import com.pms.entity.Patient;
import com.pms.repository.PatientRepository;
import com.pms.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        super();
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatients(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatients(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatients(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> searchPatients(String keyword) {
    	
    	 if (keyword == null) {
    	        return new ArrayList<>(); // Return an empty list 
    	    }
    	
        List<Patient> allPatients = patientRepository.findAll();

        List<Patient> searchResults = new ArrayList<>();

        // Iterate over the list of all patients and check if the keyword is present in any field
        for (Patient patient : allPatients) {
            if (patient.getFirstName().contains(keyword) || patient.getLastName().contains(keyword)
                    || patient.getCity().contains(keyword) || patient.getEmail().contains(keyword)) {
                searchResults.add(patient);
            }
        }

        return searchResults;
    }
    
    @Override
    public Report saveReport(Report report) {
        // Extract the patient information from the report
        Patient patient = report.getPatient();
        
        // Retrieve the existing patient from the repository
        Patient existingPatient = patientRepository.findById(patient.getId()).orElse(null);
        
        if (existingPatient != null) {
            // Update the patient's report details
            existingPatient.setTestResults(report.getTestResults());
            existingPatient.setDiagnosis(report.getDiagnosis());
            
            // Save the updated patient
            patientRepository.save(existingPatient);
            
            // Return the saved report
            return report;
        }
        
        // Handle the case where the patient is not found
         throw new RuntimeException("Patient not found with ID: " + patient.getId());
    }

    
    
   
}

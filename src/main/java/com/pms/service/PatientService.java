package com.pms.service;


import java.util.List;

import com.pms.entity.Report;
import com.pms.entity.Patient;

public interface PatientService {
	List<Patient> getAllPatients();
	Patient savePatients(Patient patient);
	
	Patient getPatientById(Long id);
	Patient updatePatients(Patient patient);
	List<Patient> searchPatients(String keyword);

	
	void deletePatients(Long id);
	Report saveReport(Report report); 
	
}

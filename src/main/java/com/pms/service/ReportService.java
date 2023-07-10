package com.pms.service;


import java.util.List;
import java.util.Optional;

import com.pms.entity.Patient;
import com.pms.entity.Report;

public interface ReportService {
    Report saveReport(Report report);
    // Add other methods for retrieving, updating, and deleting reports if needed

    List<Report> getReportsByPatient(Patient patient);
   
}

package com.pms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pms.entity.Patient;
import com.pms.entity.Report;
import com.pms.repository.ReportRepository;
import com.pms.service.*;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

	
	@Override
	public List<Report> getReportsByPatient(Patient patient) {
	    return reportRepository.findByPatient(patient);
	}
	
	
    

    // Implement other methods for retrieving, updating, and deleting reports if needed
}


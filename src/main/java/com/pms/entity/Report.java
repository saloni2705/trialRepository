package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "test_results", length = 1000)
    private String testResults;

    @Column(name = "diagnosis", length = 1000)
    private String diagnosis;


    public Report() {
        // Default constructor
    }

    public Report(Long id, Patient patient, String testResults, String diagnosis) {
        this.id = id;
        this.patient = patient;
        this.testResults = testResults;
        this.diagnosis = diagnosis;
      
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    @Override
    public String toString() {
        return "Report [id=" + id + ", patient=" + patient + ", testResults=" + testResults + ", diagnosis=" + diagnosis
                + "]";
    }

}

package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pms.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}

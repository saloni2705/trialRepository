package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	public boolean existsByEmail(String email);

	public UserDtls findByEmail(String email);

}
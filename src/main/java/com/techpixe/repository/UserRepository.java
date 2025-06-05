package com.techpixe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail(String email);
	
	boolean existsByEmail(String email); // more efficient for checking existence
	
	User findByMobileNumber(String mobileNumber);
	
	long countByDepartment(String department);
}

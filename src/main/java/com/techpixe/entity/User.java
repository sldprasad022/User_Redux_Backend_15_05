package com.techpixe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
//	@NotBlank(message = "Username is mandatory")
//    @Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Username must not contain numbers or special characters")
	private String userName;
	
//	@NotBlank(message = "Email is mandatory")
//    @Email(message = "Invalid email format")
	private String email;
	
	
//	@NotBlank(message = "MobileNumber is mandatory")
//	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
	private String mobileNumber;
	
//	@NotBlank(message = "Department is mandatory")
	private String department;
	
	private Double salary;
	
//	@NotBlank(message = "Password is mandatory")
//	@Pattern(
//	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", 
//	    message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, one digit, and one special character"
//	)
	private String password;
	
	private String otp;
	
	private String role;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

		
}

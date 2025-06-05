package com.techpixe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest 
{
	private String userName;
    private String email;
    private String mobileNumber;
    private String department;
    private Double salary;
}

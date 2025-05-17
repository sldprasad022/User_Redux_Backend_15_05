package com.techpixe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.dto.UserDto;
import com.techpixe.entity.User;
import com.techpixe.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<UserDto> saveUser(@RequestParam String userName,@RequestParam String email,@RequestParam String mobileNumber,
										@RequestParam(required=false) String department,@RequestParam(required=false) Double salary,@RequestParam String password)
	{
		UserDto save = userService.saveUser(userName, email, mobileNumber, department, salary, password);
		return new ResponseEntity<UserDto>(save,HttpStatus.OK);
	}
	
	
	@GetMapping("/fetchByUserId/{userId}")
	public ResponseEntity<User> fetchByUserId(@PathVariable("userId") Long id)
	{
		User fetchById = userService.fetchByUserId(id);
		return new ResponseEntity<User>(fetchById,HttpStatus.OK);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<User>> fetchAllUsers()
	{
		List<User> fetchedAllUsers = userService.fetchAllUsers();
		return new ResponseEntity<List<User>>(fetchedAllUsers,HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUserId(@PathVariable Long userId,@RequestParam(required=false) String userName,@RequestParam(required = false) String email,@RequestParam(required=false) String mobileNumber,@RequestParam(required=false) String department,@RequestParam(required=false) Double salary)
	{
		Optional<User> updatedUser = userService.updateUserById(userId, userName, email, mobileNumber, department, salary);
		return updatedUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		//return updatedUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/deleteByUserId/{userId}")
	public ResponseEntity<Void> deleteById(@PathVariable Long userId)
	{
		User userFound = userService.fetchByUserId(userId);
		if (userFound!=null)
		{
			System.err.println("User Deleted");
			userService.deleteByUserId(userId);
			return new  ResponseEntity<>(HttpStatus.OK);
		} 
		else 
		{
			System.err.println("****UserController Delete method if the UserId was not Found****");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//-----------------------------------------------------------------------
	
	@GetMapping("/fetchAllUsersWithSorting/{field}")
	public ResponseEntity<List<User>> fetchAllUserswithSorting(@PathVariable String field)
	{
		List<User> fetchedAllUsers = userService.fetchAllUserswithSorting(field);
		return new ResponseEntity<List<User>>(fetchedAllUsers,HttpStatus.OK);
	}
	
	
	@GetMapping("/fetchAllUsersWithPagination/{offset}/{pageSize}")
	public ResponseEntity<Page<User>> fetchAllUsersWithPagination(@PathVariable int offset,@PathVariable int pageSize)
	{
		Page<User> fetchedAllUsers = userService.fetchAllUsersWithPagination(offset,pageSize);
		return new ResponseEntity<Page<User>>(fetchedAllUsers,HttpStatus.OK);
	}
	
	
	@GetMapping("/fetchAllUsersWithPaginationAndSorting/{offset}/{pageSize}/{field}")
	public ResponseEntity<Page<User>> fetchAllUsersWithPagination(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field)
	{
		Page<User> fetchedAllUsers = userService.fetchAllUsersWithPaginationWithSorting(offset,pageSize,field);
		return new ResponseEntity<Page<User>>(fetchedAllUsers,HttpStatus.OK);
	}
	
	
}

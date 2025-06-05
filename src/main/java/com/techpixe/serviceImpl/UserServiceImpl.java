package com.techpixe.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.techpixe.dto.UserDto;
import com.techpixe.entity.User;
import com.techpixe.repository.UserRepository;
import com.techpixe.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto saveUser(String userName, String email,String mobileNumber,String department,Double salary,String password) 					  
	{
		
//		User existingEmail = userRepository.findByEmail(email);
//		
//		if (existingEmail!=null)
//		{
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already Exists");
//		}
		
		if (userRepository.existsByEmail(email)) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setMobileNumber(mobileNumber);
		user.setDepartment(department);
		user.setSalary(salary);
		user.setPassword(password);
		user.setRole("ROLE_USER");
		
		User savedUser= userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto.setUserName(savedUser.getUserName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setDepartment(savedUser.getDepartment());
		
		return userDto;
	}

	@Override
	public User fetchByUserId(Long userId) 
	{
		System.err.println("User FetchByUserId method for deleting for checking wheather the userId exists or not");
		return userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with this "+userId+" not Found"));
	}

	@Override
	public List<User> fetchAllUsers()
	{
		List<User> fetchAll = userRepository.findAll();
		return fetchAll;
	}
	
	
	
	
	//model-1 delete
//	@Override
//	public void deleteByUserId(Long userId) 
//	{
//		userRepository.deleteById(userId);
//	}
	
	//model-2 delete
	@Override
	public void deleteByUserId(Long id) {
	    User user = fetchByUserId(id); 
	    userRepository.delete(user);
	}
	
	@Override
	public Optional<User> updateUserById(Long userId, String userName, 
										 String email, String mobileNumber,
										 String department, Double salary) {
		return userRepository.findById(userId).map(existingUser->{
			existingUser.setUserName(userName!=null ? userName: existingUser.getUserName());
			existingUser.setEmail(email!=null ? email: existingUser.getEmail());
			existingUser.setMobileNumber(mobileNumber !=null ? mobileNumber : existingUser.getMobileNumber());
			existingUser.setDepartment(department!=null ? department : existingUser.getDepartment());
			existingUser.setSalary(salary!=null ? salary : existingUser.getSalary());
			
			return userRepository.save(existingUser);
		});
	}
	
	
//	@Override
//	public Optional<User> updateUserById(Long userId, String userName, String email, String mobileNumber, String department, Double salary) {
//	    return userRepository.findById(userId).map(existingUser -> {
//	        if (userName != null) existingUser.setUserName(userName);
//	        if (email != null) existingUser.setEmail(email);
//	        if (mobileNumber != null) existingUser.setMobileNumber(mobileNumber);
//	        if (department != null) existingUser.setDepartment(department);
//	        if (salary != null) existingUser.setSalary(salary);
//
//	        return userRepository.save(existingUser);
//	    });
//	}

	
//-------------------------------------------------------------------------------------------------	
	
	//Fetch All With Sorting based on field
		@Override
		public List<User> fetchAllUserswithSorting(String field)
		{
			List<User> fetchAll = userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
			if (fetchAll.isEmpty())
			{
				throw new ResponseStatusException(HttpStatus.OK,"No Records Found");
			}
			return fetchAll;
		}
		
		//Pagination
		@Override
		public Page<User> fetchAllUsersWithPagination(int offset,int pageSize)
		{
			Page<User> fetchAll = userRepository.findAll(PageRequest.of(offset, pageSize));
			if (fetchAll.isEmpty())
			{
				throw new ResponseStatusException(HttpStatus.OK,"No Records Found");
			}
			return fetchAll;
		}
		
		
		//Pagination with Sorting 
		@Override
		public Page<User> fetchAllUsersWithPaginationWithSorting(int offset,int pageSize,String field)
		{
			Page<User> fetchAll = userRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
			if (fetchAll.isEmpty())
			{
				throw new ResponseStatusException(HttpStatus.OK,"No Records Found");
			}
			return fetchAll;
		}

	
	
	

	


	
	
	
	
	
	
	
	
	
	
	

}

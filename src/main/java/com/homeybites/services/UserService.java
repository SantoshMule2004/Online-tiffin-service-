package com.homeybites.services;

import java.util.List;

import com.homeybites.payloads.OtpDto;
import com.homeybites.payloads.UserDto;

public interface UserService {
	
	//register new user
	UserDto registerNewUser(UserDto userDto);
	 
	//creates new user
	UserDto saveUser(UserDto userDto);
	
	//updates user
	UserDto updateUser(UserDto userDto, Integer userId);
	
	//updating contact details
	UserDto updateContactDetails(UserDto userDto, Integer userId);
	
	//get single user 
	UserDto getUser(Integer userId);
	
	//get single user by email id 
	UserDto getUserByEmail(String emailId);
	
	//get single user by email id 
	boolean isUserPresent(String username);
	
	//get all users
	List<UserDto> getAllUser();
	
	//delete user
	void deleteUser(Integer userId);
	
	//sending otp for verification
	OtpDto sendOtp(String username);
	
	//verifying email
	boolean VerifyOtp(String enteredOtp, String username);
	
	//forget password
	boolean forgetPassword(String username);
}

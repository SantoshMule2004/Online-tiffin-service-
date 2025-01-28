package com.homeybites.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.homeybites.entities.User;
import com.homeybites.exceptions.ResourceNotFoundException;
import com.homeybites.payloads.OtpDto;
import com.homeybites.payloads.UserDto;
import com.homeybites.repositories.UserRepository;
import com.homeybites.services.EmailService;
import com.homeybites.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmailService emailService;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		user.setUserRole("NORMAL_USER");
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		User saveedUser = this.userRepository.save(user);
		this.sendOtp(saveedUser.getEmailId());
		
		return this.modelMapper.map(saveedUser, UserDto.class);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		User save = this.userRepository.save(user);

		return this.modelMapper.map(save, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {

		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setMiddleName(user.getMiddleName());
		existingUser.setPhoneNo(user.getPhoneNo());
		existingUser.setGender(user.getGender());
		existingUser.setDob(user.getDob());
		existingUser.setUniversityName(user.getUniversityName());
		existingUser.setCourse(user.getCourse());
		existingUser.setUniversityName(user.getUniversityName());
		existingUser.setBusinessName(user.getBusinessName());
		existingUser.setCompanyName(user.getCompanyName());
		existingUser.setCourse(user.getCourse());

		User updatedUser = this.userRepository.save(existingUser);

		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String emailId) {
		User user = this.userRepository.findByEmailId(emailId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", emailId));
		return this.modelMapper.map(user, UserDto.class);
	}
	
	@Override
	public boolean isUserPresent(String username) {
		boolean existsByEmailId = this.userRepository.existsByEmailId(username);
		return existsByEmailId;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> allUsers = this.userRepository.findAll();
		List<UserDto> all = allUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return all;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
	}

	@Override
	public UserDto updateContactDetails(UserDto userDto, Integer userId) {
		
		return null;
	}

	@Override
	public boolean forgetPassword(String username) {
		
		return false;
	}

	@Override
	public OtpDto sendOtp(String username) {
		OtpDto otpDto = this.emailService.generateOtp();
		
		this.emailService.saveOtp(username, otpDto);
		
		String otp = otpDto.getOtp();
		
		String subject = "Email verification";
		String message = "Your OTP for email verification for HomeBites is \n"+ otp;
		
		this.emailService.sendEmail(username, subject, message);
		
		return otpDto;
	}

	@Override
	public boolean VerifyOtp(String enteredOtp, String username) {
		
		OtpDto otpDto = this.emailService.getOtp(username);
		
		//checks if entered OTP is null or not 
		if(enteredOtp.isEmpty())
			return false;
		
		//checks if OTP is expired 
		if(otpDto.getExpirationTime().isBefore(LocalDateTime.now()))
			return false;
		
		if(enteredOtp.equals(otpDto.getOtp())) {
			this.emailService.removeOtp(username);
			return true;
		}
		return false;
	}
}

package com.homeybites.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeybites.payloads.ApiResponse;
import com.homeybites.payloads.ResetPassword;
import com.homeybites.payloads.UserDto;
import com.homeybites.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	@Autowired
	private UserService userService;

//	// register new user
//	@PostMapping("/create")
//	public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto) {
//		userDto.setUserRole("NORMAL_USER");
//		boolean isPresent = this.userService.isUserPresent(userDto.getEmailId());
//
//		if (isPresent) {
//			return new ResponseEntity<ApiResponse>(new ApiResponse("User already exists..", false, null),
//					HttpStatus.CONFLICT);
//		}
//
//		if (userDto.getPassword() != null && userDto.getCpassword() != null
//				&& userDto.getPassword().equals(userDto.getCpassword())) {
//
//			// encoding password
//			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//T
//			UserDto savedUser = this.userService.saveUser(userDto);
//			return new ResponseEntity<ApiResponse>(new ApiResponse("User registered successfully..!", false, savedUser),
//					HttpStatus.CREATED);
//		}
//
//		return new ResponseEntity<ApiResponse>(
//				new ApiResponse("Password and confirm password does not match..!", false, null),
//				HttpStatus.BAD_REQUEST);
//	}

	// update user
	@PutMapping("/{userId}")
	public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User updated successfully..!", false, updateUser),
				HttpStatus.OK);
	}

	// getting logged in user
	@GetMapping("/current-user")
	public ResponseEntity<ApiResponse> getLoggedInUser(Principal principal) {
		String name = principal.getName();
		UserDto userDto = this.userService.getUserByEmail(name);
		return new ResponseEntity<ApiResponse>(new ApiResponse("current user", true, userDto), HttpStatus.OK);
	}

	// get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponse> getUser(@PathVariable Integer userId) {
		UserDto user = this.userService.getUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User found..!", false, user), HttpStatus.FOUND);
	}

	// get user by email id
	@GetMapping("/email")
	public ResponseEntity<ApiResponse> getUserByEmail(@RequestParam String emailId) {
		UserDto user = this.userService.getUserByEmail(emailId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User found..!", false, user), HttpStatus.FOUND);
	}

	// get all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> allUser = this.userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.FOUND);
	}

	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully..!", true, null),
				HttpStatus.NO_CONTENT);
	}

	// reset password
	@PostMapping("/reset-password")
	public ResponseEntity<ApiResponse> ResetPasswordHandler(@Valid @RequestBody ResetPassword resetPassword,
			Principal principal) {
		String name = principal.getName();
		UserDto userDto = this.userService.getUserByEmail(name);

		if (passwordEncoder.matches(resetPassword.getOldPassword(), userDto.getPassword())) {

			if (resetPassword.getNewPassword() != null && resetPassword.getcPassword() != null
					&& resetPassword.getNewPassword().equals(resetPassword.getcPassword())) {
				userDto.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
				this.userService.saveUser(userDto);
				return new ResponseEntity<ApiResponse>(new ApiResponse("Password updated successfully..!", true, null),
						HttpStatus.OK);
			}
			return new ResponseEntity<ApiResponse>(
					new ApiResponse("new password and confirm password does not match", false, null),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse("Wrong password..!", false, null),
				HttpStatus.BAD_REQUEST);
	}
}

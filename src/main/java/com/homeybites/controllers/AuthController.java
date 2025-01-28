package com.homeybites.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeybites.Security.JwtHelper;
import com.homeybites.payloads.ApiResponse;
import com.homeybites.payloads.JwtRequest;
import com.homeybites.payloads.JwtResponse;
import com.homeybites.payloads.UserDto;
import com.homeybites.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	// login
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> verifyUser(@Valid @RequestBody JwtRequest jwtRequest) {
		this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		String token = jwtHelper.generateToken(jwtRequest.getUsername());
		JwtResponse response = new JwtResponse();
		response.setMessage("User logged in successfully..!");
		response.setStatus("success");
		response.setToken(token);

		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);

	}

	private void doAuthenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {

			authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password!");
		}
	}

	// new user register
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDto userDto, HttpSession session) {
		boolean isPresent = this.userService.isUserPresent(userDto.getEmailId());

		if (isPresent) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("User already exists..", false, null),
					HttpStatus.CONFLICT);
		}

		if (userDto.getPassword() != null && userDto.getCpassword() != null
				&& userDto.getPassword().equals(userDto.getCpassword())) {

			session.setAttribute("userDto", userDto);
//			UserDto registeredUser = this.userService.saveUser(userDto);
			this.userService.sendOtp(userDto.getEmailId());

			return new ResponseEntity<ApiResponse>(
					new ApiResponse("email-verification OTP has sent to your email id (valid only for 5 minutes)", true,
							null),
					HttpStatus.CREATED);
		}

		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Password and confirm password does not match..!", false, null),
				HttpStatus.BAD_REQUEST);
	}

	// verifying email through OTP
	@PostMapping("/verify-email")
	public ResponseEntity<ApiResponse> verifyEmail(@RequestParam String otp, HttpSession session) {

		UserDto userDto = (UserDto) session.getAttribute("userDto");

		if (otp.isEmpty())
			return new ResponseEntity<ApiResponse>(
					new ApiResponse("Please, enter the otp sent to your email id", false, null),
					HttpStatus.BAD_REQUEST);

		boolean verifiedOtp = this.userService.VerifyOtp(otp, userDto.getEmailId());
		
		if (verifiedOtp) {
			session.removeAttribute("userDto");
			userDto.setVerified(true);
			UserDto registeredUser = this.userService.registerNewUser(userDto);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse("Registeration successfully..!", true, registeredUser), HttpStatus.OK);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse("OTP does not match..!", false, null),
				HttpStatus.BAD_REQUEST);
	}

	// Re-sending OTP
	@PostMapping("/resend-otp")
	public ResponseEntity<ApiResponse> sendOtp(@RequestParam String username, HttpSession session) {
		this.userService.sendOtp(username);
		session.setAttribute("username", username);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("OTP sent to your email-id successfully..! (validte for only 5 minutes.)", true, null),
				HttpStatus.OK);
	}

	// forget-password
	@PostMapping("/forget-password")
	public ResponseEntity<ApiResponse> forgetPassword(@RequestParam String username) {
		boolean userPresent = this.userService.isUserPresent(username);
		
		if(!userPresent) 
			return new ResponseEntity<ApiResponse>(new ApiResponse("User does not exists with email id: "+username, false, null),
					HttpStatus.CONFLICT);
		
		this.userService.sendOtp(username);
		ApiResponse response = new ApiResponse("OTP sent to your email id (validate for 5 minutes", true, null);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
}

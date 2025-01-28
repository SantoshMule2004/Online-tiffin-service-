package com.homeybites.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.homeybites.Security.CustomUserDetailService;
import com.homeybites.Security.JwtAuthenticationEntryPoint;
import com.homeybites.Security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(customizer->customizer.disable())
				.cors(ccustomizer->ccustomizer.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(request->request.requestMatchers("/api/v1/auth/**").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(ex->ex.authenticationEntryPoint(point))
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	protected AuthenticationProvider provider() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService());
		daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return daoProvider;
	}

	@Bean
	protected AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));;
		corsConfiguration.setAllowedHeaders(Arrays.asList("Autorization", "Content-Type", "Accept"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setMaxAge(3600L);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
	
//	.requestMatchers("/api/v1/auth/login").permitAll()
//	.requestMatchers("/api/v1/users/create").permitAll()
//	.requestMatchers("/api/v1/users/verify-otp").permitAll()
//	.requestMatchers("/v3/api-docs").permitAll()
}

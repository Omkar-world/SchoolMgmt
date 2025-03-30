package com.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
				.authorizeHttpRequests(auth -> auth.requestMatchers("/teacher-api/**").authenticated()
						.requestMatchers("stud-api/**").authenticated().anyRequest().permitAll())
				.httpBasic(); // Enable Basic Authentication

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager cretaeInMemoryDatails() {
		UserDetails details = User.withDefaultPasswordEncoder().username("Omii").password("Name").authorities("STUDENT")
				.build();
		UserDetails details1 = User.withDefaultPasswordEncoder().username("Shiv").password("sam").authorities("TEACHER")
				.build();
		return new InMemoryUserDetailsManager(details, details1);
	}

}

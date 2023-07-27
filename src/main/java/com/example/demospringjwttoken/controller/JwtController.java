package com.example.demospringjwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringjwttoken.helper.JwtUtil;
import com.example.demospringjwttoken.model.JwtRequest;
import com.example.demospringjwttoken.model.JwtResponse;
import com.example.demospringjwttoken.service.CustomeUserDetailService;

@RestController
public class JwtController 
{
	/*AuthenticationManager is a static class that manages the authentication modules that an application uses.
	 *  When a request is made to protected resources, the AuthenticationManager calls the
	 *  Authenticate method to get an Authorization instance to use in subsequent requests.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomeUserDetailService customeUserDetailService;
	
	@Autowired
	private  JwtUtil jwtUtil;
	
	
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println("jjj "  + jwtRequest );
		try 
		{
			//username and password api didhu authenticationManager ne.
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),jwtRequest.getPassword()));
		}
		catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}
		
		//if authentiate is true then generate the token.
		UserDetails userDetails = this.customeUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT " + token);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}

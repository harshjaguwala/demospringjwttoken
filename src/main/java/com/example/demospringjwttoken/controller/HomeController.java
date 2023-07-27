package com.example.demospringjwttoken.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController 
{
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "this is page";
	}
	
	@RequestMapping("/getUsers")
	public String getUsers()
	{
		return "this is get user page";
	}
}

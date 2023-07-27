package com.example.demospringjwttoken.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*The UserDetailsService is a core interface in Spring Security framework,
  which is used to retrieve the user's authentication and authorization information.
 */

@Service
public class CustomeUserDetailService implements UserDetailsService
{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		if(username.equals("harsh"))
		{
			return new User("harsh","harsh123",new ArrayList<>());
		}
		else
		{
			throw new UsernameNotFoundException("user not found");
		}
	}
	
}

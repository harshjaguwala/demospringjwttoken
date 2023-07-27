package com.example.demospringjwttoken.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demospringjwttoken.helper.JwtUtil;
import com.example.demospringjwttoken.service.CustomeUserDetailService;

@Component
/*Filter base class that aims to guarantee a single execution per request
* dispatch, on any servlet container. It provides a {@link #doFilterInternal}
* method with HttpServletRequest and HttpServletResponse arguments*/

public class JwtAuthenticationFilter extends OncePerRequestFilter 
{
	@Autowired
	CustomeUserDetailService CustomeUserDetailService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		//get token, Bearer start or not , validate
		String requesttokenHeader = request.getHeader("Authorization");
		String username=null;
		String JwtToken=null;
		
		if(requesttokenHeader!= null && requesttokenHeader.startsWith("Bearer "))
		{
			JwtToken=requesttokenHeader.substring(7);
			
			//check null and format
			try 
			{
				//token prthi username get thse.
				username = this.jwtutil.extractUsername(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			//security
			UserDetails userDetails = this.CustomeUserDetailService.loadUserByUsername(username);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("token is Invalid");
			}
		}
		filterChain.doFilter(request, response);
	}
	
}

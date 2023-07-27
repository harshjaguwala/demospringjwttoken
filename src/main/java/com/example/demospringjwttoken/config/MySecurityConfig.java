package com.example.demospringjwttoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demospringjwttoken.service.CustomeUserDetailService;


@Configuration
@EnableWebSecurity
//extends beacuse method override karine default security configuration che ene change kari sake
/*WebSecurityConfigurerAdapter is an abstract class provided by the Spring Security module. 
  Generally, we use it to override its configure() methods in order to define our security configuration class.
*/
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomeUserDetailService customeUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter JwtAuthenticationFilter;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	
	/*which url pemit and not*/
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.csrf().disable()
			.cors().disable()
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		http.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	//which authentication we used then used this configure like inMemory or else.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(customeUserDetailService);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
}

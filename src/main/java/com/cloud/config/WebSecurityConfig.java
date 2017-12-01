package com.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cloud.security.CustomUserService;
//将url 权限分配给角色
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	UserDetailsService customUserService(){ 
		return new CustomUserService(); 
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()); 
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						.anyRequest().authenticated()
						.antMatchers("/css/**").permitAll()
						.and()
						.formLogin()
							.loginPage("/login")
							.failureUrl("/login?error")
							.permitAll()
						.and()
						.logout().permitAll(); 
	}
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		
//		web.ignoring().antMatchers("/**");
//		//web.antMatchers("/admin/**").hasRole("ROLE_ADMIN");
//	}
}

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
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
        .anyRequest()
        .authenticated()
        .antMatchers("/css/**")
        .permitAll().and().formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .permitAll().and()
        .logout()
        .permitAll();*/
        
        http.authorizeRequests()
        .antMatchers("/home").permitAll()//其他地址的访问均需验证权限
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")  //指定登录页是"/login"
        .defaultSuccessUrl("/list")  //登录成功后默认跳转到"list"
        .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/home")  //退出登录后的默认url是"/home"
        .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        // //web.antMatchers("/admin/**").hasRole("ROLE_ADMIN");
    }
}

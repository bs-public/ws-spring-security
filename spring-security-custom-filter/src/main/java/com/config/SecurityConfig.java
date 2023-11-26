package com.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //@formatter:off
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .httpBasic();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth //
        .inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}password") // NoOpPasswordEncoder for inMemoryAuthentication
        .roles("ADMIN");
  }
 //@formatter:on

}

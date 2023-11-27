package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  @Qualifier("userDetailServiceImpl")
  private UserDetailsService userDetailsService;

  // @formatter:off
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth //
        .userDetailsService(userDetailsService) //
        .passwordEncoder(passwordEncoder); //
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http //
        .authorizeRequests() //
        .antMatchers("/v1/**")
        .authenticated() // apis required authentication
        .anyRequest()
        .permitAll() //
        .and() //
        .formLogin()
        .permitAll();
  }
  // @formatter:on

}

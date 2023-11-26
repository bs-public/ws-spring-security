package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.user.name}")
    private String name;

    @Value("${app.user.password}")
    private String password;

    @Value("${app.user.roles}")
    private String roles;

  //@formatter:off
  @Override
  public void configure(WebSecurity web) throws Exception {
      web.ignoring()
      	.antMatchers("/webjars/**", "/css/**");
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
      .logout()
        .permitAll();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
       User.withUsername(name)
        .password(password)
        .roles(roles)
        .build();
    return new InMemoryUserDetailsManager(user);
  }
 //@formatter:on

    @Bean
    public BCryptPasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
    }

}

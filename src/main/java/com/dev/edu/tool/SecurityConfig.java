package com.dev.edu.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static String ROLE_USER = "USER";
  private static String ROLE_ADMIN = "ADMIN";

  @Autowired
  private SuccessHandler successHandler;
  
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/webjars/**", "/css/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
            .antMatchers("/loginForm").permitAll()
            .antMatchers("/manage/**").hasRole(ROLE_ADMIN)
            .antMatchers("/setting/**").permitAll()
            .antMatchers("/reports/**").permitAll()
            .antMatchers("/html/**").permitAll()
            .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
            .loginPage("/loginForm")
            .failureUrl("/loginForm?error")
//            .defaultSuccessUrl("/reports", true)
            .successHandler(successHandler)
            .usernameParameter("staffId").passwordParameter("password")
        .and()
        .logout()
            .logoutSuccessUrl("/loginForm")
            .invalidateHttpSession(true);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new Pbkdf2PasswordEncoder();
  }
}

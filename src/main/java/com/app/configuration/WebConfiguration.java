package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.app.entry.LogoutRestEntryPoint;
import com.app.entry.RestEntryPoint;

@Configuration
@EnableWebSecurity(debug = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
			auth.inMemoryAuthentication()
			.withUser("patartimotius").password("evievi123").roles("USER");
		
	}
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }
	 
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	/*	
		http.csrf().disable()
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
            .anonymous()
        .and()
            .exceptionHandling().authenticationEntryPoint(new RestEntryPoint())
           .and()
        .logout()
   	     .logoutUrl("/oauth/logout")
   	     .logoutSuccessHandler(new LogoutRestEntryPoint())
   	     .deleteCookies("JSESSIONID")
   	     .permitAll()
   	     .and()
         .authorizeRequests()
         .antMatchers("/manager/**").permitAll() 
         .antMatchers("/oauth/revoke-token").permitAll() 
         .antMatchers("/oauth/authorize**").permitAll() 
          
         .anyRequest().authenticated();
         */
		
		 http
         .antMatcher("/**")
             .authorizeRequests()
             .antMatchers("/oauth/authorize**", "/login**", "/error**")
             .permitAll()
         .and()
             .authorizeRequests()
             .anyRequest().authenticated()
         .and()
             .formLogin().permitAll();
	}
		
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	 

}

package com.note.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.note.api.security.ApiFilter;
import com.note.api.security.ApiPermissionEvaluator;

/**
 * Currently security does not match the user with the one in the DB. 
 * In the meantime authorization checks against the user in DB.
 * @author annavardanova
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private BasicAuthenticationEntryPoint apiAuthenticationEntryPoint;
	
	@Autowired
	private ApiPermissionEvaluator apiPermissionEvaluator;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//can add more in memory users
        auth.inMemoryAuthentication()
          .withUser("test@test.com").password(passwordEncoder().encode("testPass"))
          .authorities("ROLE_USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/securityNone", "/v2/api-docs", "/swagger-ui.html", "/swagger-resources/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic()
          .authenticationEntryPoint(apiAuthenticationEntryPoint)
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and();
 
        http.addFilterAfter(new ApiFilter(),
          BasicAuthenticationFilter.class);
    }
 
    @SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();//new BCryptPasswordEncoder();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
    	DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(apiPermissionEvaluator);
    	web.expressionHandler(handler);
	}

}

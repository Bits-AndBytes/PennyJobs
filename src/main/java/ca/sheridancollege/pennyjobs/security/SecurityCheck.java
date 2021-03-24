package ca.sheridancollege.pennyjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityCheck extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//****************************************
		//This must be removed when creating production level code
		http.csrf().disable();
		http.headers().frameOptions().disable();	
		//****************************************
		
		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()//allow access to the h2 console
		//allow everybody access to the root page, as well as static folders, as well as any new page created with '/**'
		.antMatchers("/", "/css/**").permitAll() 
		.anyRequest().authenticated()
		.and();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("Weiye").password("123").roles("ADMIN")
			.and()
			.withUser("Patrick").password("123").roles("ADMIN")
			.and()
			.withUser("Skye").password("123").roles("ADMIN")
			.and()
			.withUser("Dimitri").password("123").roles("ADMIN");
	}

}

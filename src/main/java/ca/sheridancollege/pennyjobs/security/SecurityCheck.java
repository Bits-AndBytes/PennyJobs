package ca.sheridancollege.pennyjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityCheck {
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

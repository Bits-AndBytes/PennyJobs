package ca.sheridancollege.pennyjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@EnableWebSecurity
public class SecurityCheck extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoginAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//********************************************
				//This must be removed when creating production code
				http.csrf().disable();
				http.headers().frameOptions().disable();
				//********************************************
				http.authorizeRequests()
					//specific URL is restricted to the specific role
					//antMatchers are for URLS not HTMLs
//					.antMatchers("/student").hasRole("STUDENT")
//					.antMatchers("/parent").hasRole("PARENT")
//					.antMatchers("/poster").hasRole("POSTER")
					.antMatchers("/accountredirectpage").hasAnyRole("STUDENT", "PARENT", "POSTER")
					.antMatchers("/admin").hasAnyRole("STUDENT", "PARENT", "POSTER")
					
					.antMatchers("/register").permitAll()
					.antMatchers(HttpMethod.POST, "/register").permitAll()			
					.antMatchers("/h2-console/**").permitAll()
					//http://localhost:8080/css/mycss.css
					//everyone has access
					.antMatchers("/", "/js/**", "/images/**", "/css/**", "/**").permitAll() 
					.anyRequest().authenticated()
					.and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/user")
						.permitAll()
					.and()
					.logout()
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/?logout")
						.permitAll()
					.and()
					.exceptionHandling()
						.accessDeniedHandler(accessDeniedHandler);
				

	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

}

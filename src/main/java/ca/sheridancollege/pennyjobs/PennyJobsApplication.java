package ca.sheridancollege.pennyjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PennyJobsApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PennyJobsApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(PennyJobsApplication.class, args);
		//Test Commit - Chen
		//Test Commit - Dimitri
		//Test Commit - Patrick
	}

}

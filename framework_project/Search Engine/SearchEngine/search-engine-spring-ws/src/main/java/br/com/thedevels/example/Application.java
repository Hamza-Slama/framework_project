package br.com.thedevels.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@SpringBootApplication
public class Application extends ServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
        
        private static Class<Application> applicationClass = Application.class;
}

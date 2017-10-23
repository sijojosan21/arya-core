package com.sijojosan.arya.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sijojosan.arya.core.config.AuthenticationFilter;

@ComponentScan(basePackages = { "com"})
@EnableJpaRepositories("com")
@EntityScan("com")
@SpringBootApplication
public class AryaCoreApplication {

	@Autowired
	private Environment env;
	
	@Autowired
	private AuthenticationFilter authenticationFilter; 
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(authenticationFilter);
		registrationBean.addUrlPatterns(env.getProperty("server.servlet-path")+"/secure/*");
		registrationBean.setOrder(1);

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(AryaCoreApplication.class, args);
	}

}

package com.restapi.rest_demo;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.repository.CloudVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
// implements CommandLineRunner
@SpringBootApplication
public class RestDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestDemoApplication.class);
	}

}
//http://localhost:8080/swagger-ui.html
// http://localhost:8080/api-docs

package com.restapi.rest_demo;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.repository.CloudVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class RestDemoApplication implements CommandLineRunner {

	@Autowired
	CloudVendorRepository cloudVendorRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CloudVendor cloudVendorOne = new CloudVendor("Amazon","Home","xxxx");
		cloudVendorRepository.save(cloudVendorOne);

		cloudVendorRepository.findByVendorName("Amazon").forEach(
                System.out::println
        );
	}
}
//http://localhost:8080/swagger-ui.html
// http://localhost:8080/api-docs
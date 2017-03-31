package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.camel.SampleCamelApplication;

// from camel-examples/camel-example-spring-boot
@SpringBootApplication
public class SpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(SampleCamelApplication.class, args);

	}
}

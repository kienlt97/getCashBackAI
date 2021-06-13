package com.kienlt.CashBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class CashBackApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(CashBackApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CashBackApplication.class);
	}
}

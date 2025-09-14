package com.mmebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.mmebot.common.SchemaConstant.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
@ConfigurationPropertiesScan(basePackages = ROOT_PACKAGE)
public class MmebotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmebotApplication.class, args);
	}

}

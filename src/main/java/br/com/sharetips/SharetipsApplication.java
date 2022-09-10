package br.com.sharetips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SharetipsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SharetipsApplication.class, args);
	}
}

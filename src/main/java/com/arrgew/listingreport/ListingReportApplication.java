package com.arrgew.listingreport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListingReportApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ListingReportApplication.class, args);
	}

	@Override
	public void run(String... args){
		System.out.println("xd haha this is something isn't it?");
	}

}

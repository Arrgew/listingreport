package com.arrgew.listingreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.arrgew.listingreport.command","com.arrgew.listingreport.service","com.arrgew.listingreport.shell"})
public class ListingReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListingReportApplication.class, args);
    }

}

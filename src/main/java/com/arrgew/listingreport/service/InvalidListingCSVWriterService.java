package com.arrgew.listingreport.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class InvalidListingCSVWriterService {
    public InvalidListingCSVWriterService() {}

    public void writeToCSV(List<String> invalidListings){
        File csvOutputFile = new File("importLog.csv");
        try(PrintWriter printWriter = new PrintWriter(csvOutputFile)){
            invalidListings.forEach(printWriter::println);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

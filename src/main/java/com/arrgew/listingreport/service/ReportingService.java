package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportingService {
    @Autowired
    private final FtpUploadService ftpUploadService = new FtpUploadService();

    public void createReport(ListingService listingService) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM");

        List<Listing> listingsFromDB = listingService.getListings();
        Map<String,Report> monthlyReports = new LinkedHashMap<>();
        LocalDate startDate = LocalDate.of(2016,10,1);
        LocalDate endDate= LocalDate.of(2019,1,1);
        String reportFileName = "report.json";

        Report totalReport = new Report("total");
        monthlyReports.put("total",totalReport);

        for(LocalDate date = startDate;date.isBefore(endDate); date = date.plusMonths(1)){
            Report blankReport = new Report(dateTimeFormatter.format(date));
            monthlyReports.put(dateTimeFormatter.format(date),blankReport);
        }

        for(Listing i : listingsFromDB){
            updateReport(monthlyReports.get("total"),i);
            if(i.getUploadTime() != null){
                LocalDate date = i.getUploadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(monthlyReports.containsKey(dateTimeFormatter.format(date))){
                    Report report = monthlyReports.get(dateTimeFormatter.format(date));
                    updateReport(report,i);
                }
            }else{
                if(monthlyReports.containsKey("null")){
                    Report report = monthlyReports.get("null");
                    updateReport(report,i);
                }else{
                    Report report = new Report("null");
                    updateReport(report,i);
                    monthlyReports.put("null",report);
                }
            }
        }
        File jsonOutputFile = new File(reportFileName);
        try(PrintWriter printWriter = new PrintWriter(jsonOutputFile)){
            printWriter.println("{\n  \"Reports\": [");
            Iterator<Report> iterator = monthlyReports.values().iterator();
            while(true){
                Report current = iterator.next();
                printWriter.print(current);
                if(iterator.hasNext()){
                    printWriter.println(",");
                }else{
                    printWriter.println("\n  ]\n}");
                    break;
                }
            }
            System.out.println("report.json successfully created");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        ftpUploadService.upload(jsonOutputFile);

    }

    public void updateReport(Report report,Listing i){
        report.putListerEmailAddress(i.getOwnerEmailAddress());
        report.setTotalListingCount(report.getTotalListingCount()+1);
        if (i.getMarketplace().getName().equals("EBAY")) {
            report.setTotalEbayListingCount(report.getTotalEbayListingCount()+1);
            report.setTotalEbayListingPrice(report.getTotalEbayListingPrice().add(i.getListingPrice()));
        } else if (i.getMarketplace().getName().equals("AMAZON")) {
            report.setTotalAmazonListingCount(report.getTotalAmazonListingCount()+1);
            report.setTotalAmazonListingPrice(report.getTotalAmazonListingPrice().add(i.getListingPrice()));
        }
    }
}

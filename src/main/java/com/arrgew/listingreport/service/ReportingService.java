package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.model.MonthlyReport;
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
        Map<String, MonthlyReport> allReports = new LinkedHashMap<>();
        LocalDate startDate = LocalDate.of(2016,9,1);
        LocalDate endDate= LocalDate.of(2019,1,1);
        String reportFileName = "report.json";
        allReports.put("total",new MonthlyReport("total"));

        for(LocalDate date = startDate;date.isBefore(endDate); date = date.plusMonths(1)){
            allReports.put(dateTimeFormatter.format(date),new MonthlyReport(dateTimeFormatter.format(date)));
        }
        for(Listing i : listingsFromDB){
            allReports.get("total").putReport(i);
            if(i.getUploadTime() != null){
                LocalDate date = i.getUploadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(allReports.containsKey(dateTimeFormatter.format(date))){
                    allReports.get(dateTimeFormatter.format(date)).putReport(i);
                }
            }else{
                if(allReports.containsKey("null")){
                    allReports.get("null").putReport(i);
                }else{
                    allReports.put("null",new MonthlyReport("null"));
                    allReports.get("null").putReport(i);
                }
            }
        }
        File jsonOutputFile = new File(reportFileName);
        try(PrintWriter printWriter = new PrintWriter(jsonOutputFile)){
            printWriter.println("{\n  \"Reports\": [");
            Iterator<MonthlyReport> monthlyReportIterator = allReports.values().iterator();
            while(true){
                MonthlyReport current = monthlyReportIterator.next();
                printWriter.print(current);
                if(monthlyReportIterator.hasNext()){
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
}

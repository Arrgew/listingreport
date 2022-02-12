package com.arrgew.listingreport.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MonthlyReport {

    private String timePeriod;
    private Map<String,Report> reports;

    //CONSTRUCTOR

    public MonthlyReport(String timePeriod) {
        this.timePeriod = timePeriod;
        this.reports = new HashMap<>();
    }

    //GETTERS AND SETTERS

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Map<String, Report> getReports() {
        return reports;
    }

    public void setReports(Map<String, Report> reports) {
        this.reports = reports;
    }

    public void updateReport(Report report,Listing listing){
        report.putListerEmailAddress(listing.getOwnerEmailAddress());
        report.setTotalListingCount(report.getTotalListingCount()+1);
        if (listing.getMarketplace().getName().equals("EBAY")) {
            report.setTotalEbayListingCount(report.getTotalEbayListingCount()+1);
            report.setTotalEbayListingPrice(report.getTotalEbayListingPrice().add(listing.getListingPrice()));
        } else if (listing.getMarketplace().getName().equals("AMAZON")) {
            report.setTotalAmazonListingCount(report.getTotalAmazonListingCount()+1);
            report.setTotalAmazonListingPrice(report.getTotalAmazonListingPrice().add(listing.getListingPrice()));
        }
    }

    public void putReport(Listing listing){
        if(reports.containsKey(listing.getCurrency())){
            updateReport(reports.get(listing.getCurrency()),listing);
        }else{
            reports.put(listing.getCurrency(),new Report(listing.getCurrency()));
        }
    }

    //TO STRING

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    {\n    \"timePeriod\":\"" + timePeriod + "\",\n    \"reportsInMonth\": [");

        if(!reports.values().isEmpty()) {
            Iterator<Report> reportIterator = reports.values().iterator();
            while (true) {
                Report current = reportIterator.next();
                stringBuilder.append(current.toString());
                if (reportIterator.hasNext()) {
                    stringBuilder.append(",");
                } else {
                    stringBuilder.append("\n      ");
                    break;
                }
            }
        }

        stringBuilder.append("]\n");
        stringBuilder.append("    }");
        return stringBuilder.toString();
    }
}

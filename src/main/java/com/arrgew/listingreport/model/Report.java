package com.arrgew.listingreport.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Report {

    private String currency;
    private Integer totalListingCount;
    private Integer totalEbayListingCount;
    private BigDecimal totalEbayListingPrice;
    private Integer totalAmazonListingCount;
    private BigDecimal totalAmazonListingPrice;
    private final Map<String,Integer> listerEmailAddresses;

    //CONSTRUCTOR
    public Report(String currency) {
        this.currency = currency;
        this.totalListingCount = 0;
        this.totalEbayListingCount = 0;
        this.totalEbayListingPrice = new BigDecimal(BigInteger.ZERO);
        this.totalAmazonListingCount = 0;
        this.totalAmazonListingPrice = new BigDecimal(BigInteger.ZERO);
        this.listerEmailAddresses = new HashMap<>();
    }

    //TO STRING
    @Override
    public String toString() {
        if(totalEbayListingCount> 0 && totalAmazonListingCount > 0){
        return "\n        {\n" +
                "          \"currency\": \"" + currency +
                "\",\n          \"totalListingCount\": " + totalListingCount +
                ",\n          \"totalEbayListingCount\": " + totalEbayListingCount +
                ",\n          \"totalEbayListingPrice\": " + totalEbayListingPrice +
                ",\n          \"averageEbayListingPrice\":  "+(totalEbayListingPrice.divide(new BigDecimal(totalEbayListingCount.toString()), RoundingMode.CEILING))+
                ",\n          \"totalAmazonListingCount\": " + totalAmazonListingCount +
                ",\n          \"totalAmazonListingPrice\": " + totalAmazonListingPrice +
                ",\n          \"averageAmazonListingPrice\": "+(totalAmazonListingPrice.divide(new BigDecimal(totalAmazonListingCount.toString()), RoundingMode.CEILING))+
                ",\n          \"bestListerEmailAddress\": \"" + getBestListerEmailAddress() +"\"\n        }";
        }else if(totalEbayListingCount > 0){
            return "\n        {\n" +
                    "          \"currency\": \"" + currency +
                    "\",\n          \"totalListingCount\": " + totalListingCount +
                    ",\n          \"totalEbayListingCount\": " + totalEbayListingCount +
                    ",\n          \"totalEbayListingPrice\": " + totalEbayListingPrice +
                    ",\n          \"averageEbayListingPrice\":  "+(totalEbayListingPrice.divide(new BigDecimal(totalEbayListingCount.toString()), RoundingMode.CEILING))+
                    ",\n          \"totalAmazonListingCount\": " + totalAmazonListingCount +
                    ",\n          \"totalAmazonListingPrice\": " + totalAmazonListingPrice +
                    ",\n          \"averageAmazonListingPrice\": "+"0"+
                    ",\n          \"bestListerEmailAddress\": \"" + getBestListerEmailAddress() +"\"\n        }";
        }else if(totalAmazonListingCount > 0){
            return "\n        {\n" +
                    "          \"currency\": \"" + currency +
                    "\",\n          \"totalListingCount\": " + totalListingCount +
                    ",\n          \"totalEbayListingCount\": " + totalEbayListingCount +
                    ",\n          \"totalEbayListingPrice\": " + totalEbayListingPrice +
                    ",\n          \"averageEbayListingPrice\":  "+"0"+
                    ",\n          \"totalAmazonListingCount\": " + totalAmazonListingCount +
                    ",\n          \"totalAmazonListingPrice\": " + totalAmazonListingPrice +
                    ",\n          \"averageAmazonListingPrice\": "+(totalAmazonListingPrice.divide(new BigDecimal(totalAmazonListingCount.toString()), RoundingMode.CEILING))+
                    ",\n          \"bestListerEmailAddress\": \"" + getBestListerEmailAddress() +"\"\n        }";
        }else {
            return "\n        {\n" +
                    "          \"currency\": \"" + currency +
                    "\",\n          \"totalListingCount\": " + totalListingCount +
                    ",\n          \"totalEbayListingCount\": " + totalEbayListingCount +
                    ",\n          \"totalEbayListingPrice\": " + totalEbayListingPrice +
                    ",\n          \"averageEbayListingPrice\":  "+"0"+
                    ",\n          \"totalAmazonListingCount\": " + totalAmazonListingCount +
                    ",\n          \"totalAmazonListingPrice\": " + totalAmazonListingPrice +
                    ",\n          \"averageAmazonListingPrice\": "+"0"+
                    ",\n          \"bestListerEmailAddress\": " + "null" +"\n        }";
        }
    }

    //GETTERS AND SETTERS
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getTotalListingCount() {
        return totalListingCount;
    }

    public void setTotalListingCount(Integer totalListingCount) {
        this.totalListingCount = totalListingCount;
    }

    public Integer getTotalEbayListingCount() {
        return totalEbayListingCount;
    }

    public void setTotalEbayListingCount(Integer totalEbayListingCount) {
        this.totalEbayListingCount = totalEbayListingCount;
    }

    public BigDecimal getTotalEbayListingPrice() {
        return totalEbayListingPrice;
    }

    public void setTotalEbayListingPrice(BigDecimal totalEbayListingPrice) {
        this.totalEbayListingPrice = totalEbayListingPrice;
    }

    public Integer getTotalAmazonListingCount() {
        return totalAmazonListingCount;
    }

    public void setTotalAmazonListingCount(Integer totalAmazonListingCount) {
        this.totalAmazonListingCount = totalAmazonListingCount;
    }

    public BigDecimal getTotalAmazonListingPrice() {
        return totalAmazonListingPrice;
    }

    public void setTotalAmazonListingPrice(BigDecimal totalAmazonListingPrice) {
        this.totalAmazonListingPrice = totalAmazonListingPrice;
    }

    public void putListerEmailAddress(String listerEmailAddress){
        Integer j = listerEmailAddresses.get(listerEmailAddress);
        listerEmailAddresses.put(listerEmailAddress, (j == null) ? 1 : j + 1);
    }

    //METHODS
    public String getBestListerEmailAddress(){
        Integer maxValueInMap = (Collections.max(listerEmailAddresses.values()));
        for(Map.Entry<String, Integer> entry : listerEmailAddresses.entrySet()){
            if(entry.getValue().equals(maxValueInMap)){
                return entry.getKey();
            }
        }
        return null;
    }
}

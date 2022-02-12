package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ListingValidatorService{
    public ListingValidatorService(){}

    private final String emailRegex = "^(.+)@(.+)$";
    private final Pattern emailPattern = Pattern.compile(emailRegex);

    public String validateListing(Listing listing){
        if(listing.getId() == null ){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",id";
        }else if(listing.getTitle() == null){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",title";
        }else if(listing.getDescription() == null){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",description";
        }else if(listing.getLocation() == null){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",location";
        }else if(listing.getListingPrice() == null || !(listing.getListingPrice().compareTo(BigDecimal.ZERO) > 0)){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",listing_price";
        }else if(listing.getCurrency() == null || listing.getCurrency().length() != 3){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",currency";
        }else if(listing.getQuantity() == null || listing.getQuantity() <= 0){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",quantity";
        }else if(listing.getListingStatus() == null ){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",listing_status";
        }else if(listing.getMarketplace() == null ){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",marketplace";
        }else if(listing.getOwnerEmailAddress() == null || !emailPattern.matcher(listing.getOwnerEmailAddress()).matches()){
            return listing.getId().toString()+","+listing.getMarketplace().getName()+",owner_email_address";
        }
        return "valid";
    }
}

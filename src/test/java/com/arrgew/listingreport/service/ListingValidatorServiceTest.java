package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.model.ListingStatus;
import com.arrgew.listingreport.model.Location;
import com.arrgew.listingreport.model.Marketplace;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ListingValidatorServiceTest {

    private final ListingValidatorService listingValidatorService = new ListingValidatorService();

    @Test
    void itShouldValidateAndTrue() throws ParseException {
        Marketplace marketplace1 = new Marketplace(1,"first marketplace");
        Location location1 = new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22");
        ListingStatus listingStatus1 = new ListingStatus(1,"ACTIVE");
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"valid");
    }

    @Test
    void itShouldValidateAndFalseMissingTitle() throws ParseException {
        Marketplace marketplace1 = new Marketplace(1,"first marketplace");
        Location location1 = new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22");
        ListingStatus listingStatus1 = new ListingStatus(1,"ACTIVE");
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),null,"Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertNotEquals(listingValidatorService.validateListing(listing1),"valid");
    }

    @Test
    void itShouldValidateAndFalseInvalidEmail() throws ParseException {
        Marketplace marketplace1 = new Marketplace(1,"first marketplace");
        Location location1 = new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22");
        ListingStatus listingStatus1 = new ListingStatus(1,"ACTIVE");
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),null,"Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"kru.eger.yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertNotEquals(listingValidatorService.validateListing(listing1),"valid");
    }

}
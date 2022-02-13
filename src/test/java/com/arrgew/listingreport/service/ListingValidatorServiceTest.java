package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.model.ListingStatus;
import com.arrgew.listingreport.model.Location;
import com.arrgew.listingreport.model.Marketplace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ListingValidatorServiceTest {

    private final ListingValidatorService listingValidatorService = new ListingValidatorService();
    private Marketplace marketplace1;
    private Location location1;
    private ListingStatus listingStatus1;

    @BeforeEach
    void setUp() {
        marketplace1 = new Marketplace(1,"first marketplace");
        location1 = new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22");
        listingStatus1 = new ListingStatus(1,"ACTIVE");
    }

    @Test
    void itShouldValidateAndTrue() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"valid");
    }

    @Test
    void itShouldValidateAndMissingTitle() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),null,"Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,title");
    }

    @Test
    void itShouldValidateAndMissingDescription()throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title",null,UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"15227.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"k.ru.ege.r@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,description");
    }

    @Test
    void itShouldValidateAndMissingLocation()throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title","just a regular description",null,"15227.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"k.ru.ege.r@yahoo.com");
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,location");
    }

    @Test
    void itShouldValidateAndInvalidListingPrice() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"0","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,listing_price");
    }

    @Test
    void itShouldValidateAndMissingListingPrice() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),null,"AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,listing_price");
    }

    @Test
    void itShouldValidateAndInvalidCurrency() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22","AUDI A8",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,currency");
    }

    @Test
    void itShouldValidateAndMissingCurrency() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22",null,38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,currency");
    }

    @Test
    void itShouldValidateAndInvalidQuantity() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22","HUF",0,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,quantity");
    }

    @Test
    void itShouldValidateAndMissingQuantity() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22","HUF",null,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,quantity");
    }

    @Test
    void itShouldValidateAndMissingListingStatus() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22","HUF",77,0,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,listing_status");
    }

    @Test
    void itShouldValidateAndMissingMarketplace() throws ParseException{
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"753.22","HUF",44,1,0, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(location1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,null,marketplace");
    }

    @Test
    void itShouldValidateAndInvalidEmail() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"kru.eger.yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,owner_email_address");
    }

    @Test
    void itShouldValidateAndInvalidEmail2() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"@kru.eger.yahoo.com");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,owner_email_address");
    }

    @Test
    void itShouldValidateAndInvalidEmail3() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"kru.eger.yahoo.com@");
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,owner_email_address");
    }

    @Test
    void itShouldValidateAndMissingEmail() throws ParseException {
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"good title","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),null);
        listing1.setLocation(location1);
        listing1.setMarketplace(marketplace1);
        listing1.setListingStatus(listingStatus1);
        assertEquals(listingValidatorService.validateListing(listing1),"10b3bc5d-2463-4d16-abb5-eb169e62ed79,first marketplace,owner_email_address");
    }
}
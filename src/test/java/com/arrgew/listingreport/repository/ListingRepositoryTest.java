package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.model.ListingStatus;
import com.arrgew.listingreport.model.Location;
import com.arrgew.listingreport.model.Marketplace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ListingRepositoryTest {

    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ListingStatusRepository listingStatusRepository;
    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @BeforeEach
    void setUp() {
        this.marketplaceRepository.save(new Marketplace(1,"first marketplace"));
        this.marketplaceRepository.save(new Marketplace(2,"second marketplace"));
        this.locationRepository.save(new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22"));
        this.locationRepository.save(new Location(UUID.fromString("6815bc59-5857-462f-8001-300ebbe3fceb"),"The manager","phonenumber","primary address","secondary address","Real country","place that exists","111 11"));
        this.listingStatusRepository.save(new ListingStatus(1,"ACTIVE"));
        this.listingStatusRepository.save(new ListingStatus(2,"NOT ACTIVE"));
    }

    @AfterEach
    void tearDown(){
        this.listingRepository.deleteAll();
        this.marketplaceRepository.deleteAll();
        this.locationRepository.deleteAll();
        this.listingStatusRepository.deleteAll();
    }

    @Test
    void findAllListingsInDatabase() throws ParseException {
        //give
        List<Listing> listings= new ArrayList<>();
        listings.add(new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com"));
        listings.add(new Listing(UUID.fromString("7430ab37-190a-4b7e-8bde-bc107dbe411b"),"Real Person","Real description, nothing to see here",UUID.fromString("6815bc59-5857-462f-8001-300ebbe3fceb"),"15700.34","HUF",8,2,2, new SimpleDateFormat("MM/dd/yyyy").parse("10/4/2017"),"realperson@gmail.com"));
        for(Listing i : listings){
            i.setLocation(locationRepository.findLocationById(i.getLocationId()));
            i.setListingStatus(listingStatusRepository.findListingStatusById(i.getListingStatusId()));
            i.setMarketplace(marketplaceRepository.findMarketplaceById(i.getMarketplaceId()));
        }
        this.listingRepository.saveAll(listings);
        //when
        List<Listing> listingRepositoryAll = listingRepository.findAll();
        //then
        assertEquals(listings,listingRepositoryAll);
    }

    @Test
    void findListingById() throws Exception{
        //give
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"157.34","AUD",38,1,1, new SimpleDateFormat("MM/dd/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        listing1.setLocation(locationRepository.findLocationById(listing1.getLocationId()));
        listing1.setListingStatus(listingStatusRepository.findListingStatusById(listing1.getListingStatusId()));
        listing1.setMarketplace(marketplaceRepository.findMarketplaceById(listing1.getMarketplaceId()));
        //when
        this.listingRepository.save(listing1);
        Listing newListing1 = this.listingRepository.getById(listing1.getId());
        //then
        assertEquals(listing1,newListing1);
    }
}
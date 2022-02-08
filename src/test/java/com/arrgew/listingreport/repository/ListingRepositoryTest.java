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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
        Marketplace marketplace1 = new Marketplace(1,"first marketplace");
        this.marketplaceRepository.save(marketplace1);
        Location location1 = new Location(UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),"Bevin Mallebone","321-392-5930","9496 Bluestem Center",null,"Sweden","Göteborg","415 22");
        this.locationRepository.save(location1);
        ListingStatus listingStatus1 = new ListingStatus(1,"ACTIVE");
        this.listingStatusRepository.save(listingStatus1);
    }

    @AfterEach
    void tearDown(){
        this.listingRepository.deleteAll();
    }

    @Test
    void findAllListingsInDatabase() throws ParseException {
        //give
        Listing listing1 = new Listing(UUID.fromString("10b3bc5d-2463-4d16-abb5-eb169e62ed79"),"Narihira Bamboo","Semiarundinaria fastuosa (Lat.-Marl. ex Mitford) Makino ex Nakai",UUID.fromString("5249f33c-fadf-44d9-ab70-471df29c20a6"),new BigDecimal(157.34),"AUD",38,1,1, new SimpleDateFormat("dd/mm/yyyy").parse("5/10/2018"),"krueger@yahoo.com");
        this.listingRepository.save(listing1);
        //when
        List<Listing> listingList = listingRepository.findAll();
        //then
        assertEquals(listing1,listingList.get(0));
    }
}
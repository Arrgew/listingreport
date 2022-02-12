package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.ListingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ListingStatusRepositoryTest {
    @Autowired
    ListingStatusRepository listingStatusRepository;
    @Test
    void getAllListingStatuses(){
        //give
        ListingStatus active = new ListingStatus(1,"ACTIVE");
        ListingStatus inActive = new ListingStatus(2,"NOT ACTIVE");
        this.listingStatusRepository.save(active);
        this.listingStatusRepository.save(inActive);
        //then
        ListingStatus activeFromDb = this.listingStatusRepository.findListingStatusById(active.getId());
        ListingStatus inActiveFromDb = this.listingStatusRepository.findListingStatusById(inActive.getId());
        //when
        assertEquals(active,activeFromDb);
        assertEquals(inActive,inActiveFromDb);
    }
}
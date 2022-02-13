package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.Marketplace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class MarketplaceRepositoryTest {
    @Autowired
    MarketplaceRepository marketplaceRepository;

    @Test
    void getMarketplacesById(){
        //give
        Marketplace ebay = new Marketplace(1,"eBay");
        Marketplace amazon = new Marketplace(2,"Amazon");
        this.marketplaceRepository.save(ebay);
        this.marketplaceRepository.save(amazon);
        //then
        Marketplace ebayFromDb = this.marketplaceRepository.findMarketplaceById(ebay.getId());
        Marketplace amazonFromDb = this.marketplaceRepository.findMarketplaceById(amazon.getId());
        //when
        assertEquals(ebay,ebayFromDb);
        assertEquals(amazon,amazonFromDb);
        assertNotEquals(ebay,amazonFromDb);
        assertNotEquals(amazon,ebayFromDb);
    }
}
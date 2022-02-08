package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.repository.ListingRepository;

public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Listing addListing(Listing newListing){
        return listingRepository.save(newListing);
    }
}

package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Listing;
import com.arrgew.listingreport.repository.ListingRepository;
import com.arrgew.listingreport.repository.ListingStatusRepository;
import com.arrgew.listingreport.repository.LocationRepository;
import com.arrgew.listingreport.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;


@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final LocationRepository locationRepository;
    private final ListingStatusRepository listingStatusRepository;
    private final MarketplaceRepository marketplaceRepository;
    private final ListingValidatorService listingValidatorService = new ListingValidatorService();
    private final InvalidListingCSVWriterService invalidListingCSVWriterService = new InvalidListingCSVWriterService();
    @Value("${listingUrl}")
    private String listingUrl;

    public ListingService(ListingRepository listingRepository, LocationRepository locationRepository, ListingStatusRepository listingStatusRepository, MarketplaceRepository marketplaceRepository) {
        this.listingRepository = listingRepository;
        this.locationRepository = locationRepository;
        this.listingStatusRepository = listingStatusRepository;
        this.marketplaceRepository = marketplaceRepository;
    }

    public void loadListings(){
        RestTemplate template = new RestTemplate();
        System.out.println("Getting listings from the api...");
        ResponseEntity<List<Listing>> response = template.exchange(listingUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Listing>>() {
                });
        List<Listing> listings = response.getBody();
        List<String> invalidListings = new LinkedList<>();
        List<Listing> listingsToRemove = new LinkedList<>();
        assert listings != null;
        for(Listing i : listings){
            i.setLocation(locationRepository.findLocationById(i.getLocationId()));
            i.setListingStatus(listingStatusRepository.findListingStatusById(i.getListingStatusId()));
            i.setMarketplace(marketplaceRepository.findMarketplaceById(i.getMarketplaceId()));
            if(!listingValidatorService.validateListing(i).equals("valid")){
                invalidListings.add(listingValidatorService.validateListing(i));
                listingsToRemove.add(i);
            }
        }
        for(Listing i : listingsToRemove){
            listings.remove(i);
        }
        invalidListingCSVWriterService.writeToCSV(invalidListings);
        listingRepository.saveAll(listings);
    }

    public List<Listing> getListings(){
        return listingRepository.findAll();
    }
}

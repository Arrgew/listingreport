package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.ListingStatus;
import com.arrgew.listingreport.repository.ListingStatusRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ListingStatusService {
    private final ListingStatusRepository listingStatusRepository;
    @Value("${listingStatusUrl}")
    private String listingStatusUrl;

    public ListingStatusService(ListingStatusRepository listingStatusRepository) {
        this.listingStatusRepository = listingStatusRepository;
    }

    public void loadListingStatuses(){
        RestTemplate template = new RestTemplate();
        System.out.println("Getting listing statuses from the api...");
        ResponseEntity<List<ListingStatus>> response = template.exchange(listingStatusUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ListingStatus>>() {
                });
        List<ListingStatus> body = response.getBody();
        assert body != null;
        listingStatusRepository.saveAll(body);
    }
}

package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Location;
import com.arrgew.listingreport.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    @Value("${locationUrl}")
    private String locationUrl;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void loadLocations(){
        RestTemplate template = new RestTemplate();
        System.out.println("Getting locations from the api...");
        ResponseEntity<List<Location>> response = template.exchange(locationUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {
                });
        List<Location> body = response.getBody();
        assert body != null;
        locationRepository.saveAll(body);
    }
}

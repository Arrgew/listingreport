package com.arrgew.listingreport.service;

import com.arrgew.listingreport.model.Marketplace;
import com.arrgew.listingreport.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MarketplaceService {
    private final MarketplaceRepository marketplaceRepository;
    @Value("${marketplaceUrl}")
    private String marketplaceUrl;


    public MarketplaceService(MarketplaceRepository marketplaceRepository) {
        this.marketplaceRepository = marketplaceRepository;
    }

    public void loadMarketplaces(){
        RestTemplate template = new RestTemplate();
        System.out.println("Getting marketplaces from the api...");
        ResponseEntity<List<Marketplace>> response = template.exchange(marketplaceUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Marketplace>>() {
                });
        List<Marketplace> body = response.getBody();
        assert body != null;
        marketplaceRepository.saveAll(body);
    }
}

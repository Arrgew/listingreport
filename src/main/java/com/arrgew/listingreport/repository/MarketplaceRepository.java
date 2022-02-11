package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace,Integer> {
    Marketplace findMarketplaceById(Integer id);
}

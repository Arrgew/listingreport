package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingStatusRepository extends JpaRepository<ListingStatus,Integer> {
    ListingStatus findListingStatusById(Integer id);
}

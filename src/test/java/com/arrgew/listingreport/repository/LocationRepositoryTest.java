package com.arrgew.listingreport.repository;

import com.arrgew.listingreport.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@DataJpaTest
class LocationRepositoryTest {
    @Autowired
    LocationRepository locationRepository;
    @Test
    void getLocationsById(){
        //give
        Location location1 = new Location(UUID.fromString("6815bc59-5857-462f-8001-300ebbe3fceb"),"The manager","phonenumber","primary address","secondary address","Real country","place that exists","111 11");
        Location location2 = new Location(UUID.fromString("1a10c438-4eea-40d5-8dbe-9b160c79ba5b"),"The real manager","phonenumber but better","The address",null,"The realest country","place that really exists","222 22");
        this.locationRepository.save(location1);
        this.locationRepository.save(location2);
        //then
        Location location1FromDb = this.locationRepository.findLocationById(location1.getId());
        Location location2FromDb = this.locationRepository.findLocationById(location2.getId());
        //when
        assertEquals(location1,location1FromDb);
        assertEquals(location2,location2FromDb);
        assertNotEquals(location1,location2FromDb);
        assertNotEquals(location2,location1FromDb);
    }
}
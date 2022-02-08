package com.arrgew.listingreport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ListingStatus {

    @Id
    @NotNull
    private Integer id;

    @Column(name="status_name")
    @NotNull
    private String statusName;

    @OneToMany(mappedBy = "listingStatus")
    private List<Listing> listings;

    //CONSTRUCTOR
    public ListingStatus(){}

    public ListingStatus(Integer id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    //GETTERS AND SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

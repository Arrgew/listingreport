package com.arrgew.listingreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ListingStatus {

    @Id
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @Column(name="status_name")
    @NotNull
    @JsonProperty("status_name")
    private String statusName;

    @OneToMany(mappedBy = "listingStatus")
    @Transient
    private List<Listing> listings;

    //CONSTRUCTOR
    public ListingStatus(){}

    public ListingStatus(Integer id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public ListingStatus(Integer id) {
        this.id = id;
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

    //TOSTRING


    @Override
    public String toString() {
        return "{id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}

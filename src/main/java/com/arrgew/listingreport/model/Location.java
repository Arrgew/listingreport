package com.arrgew.listingreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Location {

    @Column(name = "id",length = 16, unique = true, nullable = false)
    @Id
    @JsonProperty("id")
    private UUID id;

    @Column(name = "manager_name")
    @JsonProperty("manager_name")
    private String managerName;

    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "address_primary")
    @JsonProperty("address_primary")
    private String addressPrimary;

    @Column(name = "address_secondary")
    @JsonProperty("address_secondary")
    private String addressSecondary;

    @Column(name = "country")
    @JsonProperty("country")
    private String country;

    @Column(name = "town")
    @JsonProperty("town")
    private String town;

    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @OneToMany(mappedBy = "location")
    @Transient
    private List<Listing> listings;

    //CONSTRUCTOR

    public Location(){}

    public Location(UUID id, String managerName, String phone, String addressPrimary, String addressSecondary, String country, String town, String postalCode) {
        this.id = id;
        this.managerName = managerName;
        this.phone = phone;
        this.addressPrimary = addressPrimary;
        this.addressSecondary = addressSecondary;
        this.country = country;
        this.town = town;
        this.postalCode = postalCode;
    }

    public Location(UUID id){
        this.id = id;
    }

    //GETTERS AND SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public void setAddressPrimary(String addressPrimary) {
        this.addressPrimary = addressPrimary;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public void setAddressSecondary(String addressSecondary) {
        this.addressSecondary = addressSecondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "{id=" + id +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", addressPrimary='" + addressPrimary + '\'' +
                ", addressSecondary='" + addressSecondary + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

package com.arrgew.listingreport.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Validated
public class Listing {

    @Id
    @NotNull
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "listing_price")
    @NotNull
    @DecimalMin(value ="0.0",inclusive = false)
    //@Digits(integer = 10,fraction = 2)
    private BigDecimal listingPrice;

    @Column(name = "currency")
    @Length(min = 3, max = 3)
    @NotNull
    private String currency;

    @Column(name = "quantity")
    @Min(1)
    @NotNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "listing_status_id")
    @NotNull
    private ListingStatus listingStatus;

    @ManyToOne
    @JoinColumn(name = "marketplace_id")
    @NotNull
    private Marketplace marketplace;

    @Column(name = "upload_time")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date uploadTime;

    @Column(name = "owner_email_address")
    @Email
    @NotNull
    private String ownerEmailAddress;

    //CONSTRUCTOR
    public Listing(){}

    public Listing(UUID id, String title, String description, UUID locationId, BigDecimal listingPrice, String currency, Integer quantity, Integer listingStatusId, Integer marketplaceId, Date uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = new Location();
        this.location.setId(locationId);
        this.listingPrice = listingPrice;
        this.currency = currency;
        this.quantity = quantity;
        this.listingStatus = new ListingStatus();
        this.listingStatus.setId(listingStatusId);
        this.marketplace = new Marketplace();
        this.marketplace.setId(marketplaceId);
        this.uploadTime = uploadTime;
        this.ownerEmailAddress = ownerEmailAddress;
    }


    //GETTERS AND SETTERS

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public ListingStatus getListingStatus() {
        return listingStatus;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BigDecimal getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(BigDecimal listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setListingStatus(ListingStatus listingStatus) {
        this.listingStatus = listingStatus;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    //HASHCODE & EQUALS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return id.equals(listing.id) && title.equals(listing.title) && description.equals(listing.description) && location.getId().equals(listing.location.getId()) && listingPrice.equals(listing.listingPrice) && currency.equals(listing.currency) && quantity.equals(listing.quantity) && listingStatus.getId().equals(listing.listingStatus.getId()) && marketplace.getId().equals(listing.marketplace.getId()) && Objects.equals(uploadTime, listing.uploadTime) && ownerEmailAddress.equals(listing.ownerEmailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, location, listingPrice, currency, quantity, listingStatus, marketplace, uploadTime, ownerEmailAddress);
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location.getId() +
                ", listingPrice=" + listingPrice +
                ", currency='" + currency + '\'' +
                ", quantity=" + quantity +
                ", listingStatus=" + listingStatus.getId() +
                ", marketplace=" + marketplace.getId() +
                ", uploadTime=" + uploadTime +
                ", ownerEmailAddress='" + ownerEmailAddress + '\'' +
                '}';
    }
}

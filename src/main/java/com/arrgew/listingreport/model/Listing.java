package com.arrgew.listingreport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Listing {

    @Id
    @Column(name = "id",length = 16, unique = true, nullable = false)
    @JsonProperty("id")
    @NotNull
    private UUID id;

    @NotNull
    @Column(name = "title")
    @JsonProperty("title")
    @NotBlank(message = "Name is mandatory")
    private String title;

    @NotNull
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @JsonProperty("location_id")
    @Transient
    @JsonInclude
    private UUID locationId;

    @JsonProperty("listing_price")
    @Column(name = "listing_price")
    @NotNull
    @Digits(integer = 10,fraction = 2)
    @Min(0)
    private BigDecimal listingPrice;

    @Column(name = "currency")
    @Length(min = 3, max = 3)
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String currency;

    @Column(name = "quantity")
    @Min(0)
    @NotNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "listing_status_id")
    @JsonIgnore
    private ListingStatus listingStatus;

    @JsonProperty("listing_status")
    @Transient
    @JsonInclude
    private Integer listingStatusId;

    @ManyToOne
    @JoinColumn(name = "marketplace_id")
    @JsonIgnore
    private Marketplace marketplace;

    @JsonProperty("marketplace")
    @Transient
    @JsonInclude
    private Integer marketplaceId;

    @JsonProperty("upload_time")
    @Column(name = "upload_time")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date uploadTime;

    @JsonProperty("owner_email_address")
    @Column(name = "owner_email_address")
    @Email
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String ownerEmailAddress;

    //CONSTRUCTOR
    public Listing(){}

    public Listing(UUID id, String title, String description, UUID locationId, String listingPrice, String currency, Integer quantity, Integer listingStatusId, Integer marketplaceId, Date uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.locationId = locationId;
        this.location = new Location();
        this.listingPrice = new BigDecimal(listingPrice);
        this.currency = currency;
        this.quantity = quantity;
        this.listingStatusId = listingStatusId;
        this.listingStatus =new ListingStatus();
        this.marketplaceId =marketplaceId;
        this.marketplace = new Marketplace();
        this.uploadTime = uploadTime;
        this.ownerEmailAddress = ownerEmailAddress;
    }


    //GETTERS AND SETTERS

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public BigDecimal getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(String listingPrice) {
        this.listingPrice = new BigDecimal(listingPrice);
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

    public ListingStatus getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(ListingStatus listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Integer getListingStatusId() {
        return listingStatusId;
    }

    public void setListingStatusId(Integer listingStatusId) {
        this.listingStatusId = listingStatusId;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Integer getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(Integer marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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
        return Objects.hash(id, title, description, listingPrice, currency, quantity, uploadTime, ownerEmailAddress);
    }

    //TOSTRING

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", listingPrice=" + listingPrice +
                ", currency='" + currency + '\'' +
                ", quantity=" + quantity +
                ", marketplace=" + marketplace +
                ", listingStatus=" + listingStatus +
                ", uploadTime=" + uploadTime +
                ", ownerEmailAddress='" + ownerEmailAddress + '\'' +
                '}';
    }
}

package com.arrgew.listingreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Marketplace {

    @Id
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @Column(name="marketplace_name")
    @JsonProperty("marketplace_name")
    private String name;

    @OneToMany(mappedBy = "marketplace")
    @Transient
    private List<Listing> listings;

    //CONSTRUCTOR
    public Marketplace(){}

    public Marketplace(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Marketplace(Integer id) {
        this.id = id;
        this.name = null;
    }

    //GETTERS AND SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //HASH AND EQUALS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marketplace)) return false;
        Marketplace that = (Marketplace) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

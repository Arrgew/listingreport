package com.arrgew.listingreport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Marketplace {

    @Id
    @NotNull
    private Integer id;

    @NotNull
    @Column(name="marketplace_name")
    private String name;

    @OneToMany(mappedBy = "marketplace")
    private List<Listing> listings;

    //CONSTRUCTOR
    public Marketplace(){}

    public Marketplace(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}

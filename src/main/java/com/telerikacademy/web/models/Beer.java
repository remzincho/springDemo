package com.telerikacademy.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="beer_id")
    private int id;
    @NotNull(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols")
    @Column(name="name")
    private String name;
    @Positive(message = "ABV should be positive")
    @Column(name="abv")
    private double abv;

//    @NotNull
//    private Style style;

    public Beer() {
    }

    public Beer(int id, String name, double abv) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        //this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

//    public Style getStyle() {
//        return style;
//    }
//
//    public void setStyle(Style style) {
//        this.style = style;
//    }
}

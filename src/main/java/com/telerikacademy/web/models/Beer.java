package com.telerikacademy.web.models;

import javax.persistence.*;

@Entity
@Table(name="beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="beer_id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="abv")
    private double abv;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User ownerId;
    @ManyToOne
    @JoinColumn(name="style_id")
    private Style style;

    public Beer() {
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }
}

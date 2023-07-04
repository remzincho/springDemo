package com.telerikacademy.web.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="style_id")
    private int id;
    @Size(min=1, max=35, message = "Style name should be between 1 and 35 symbols.")
    @Column(name="name")
    private String name;

    public Style() {
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
}

package com.telerikacademy.web.models;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Style {
    @Positive
    private int id;
    @Size(min=1, max=35, message = "Style name should be between 1 and 35 symbols.")
    private String name;

    public Style() {
    }

    public Style(int id, String name) {
        this.id = id;
        this.name = name;
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

package com.telerikacademy.web.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String type, String attribute, String value) {
        super(String.format("%s with %s %s already exists.", type, attribute, value));
    }

    public DuplicateEntityException(String type, String attribute, int value) {
        super(String.format("%s with %s %d already exists.", type, attribute, value));
    }
}

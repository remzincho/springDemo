package com.telerikacademy.web.models.DTOs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull(message = "Username can't be empty")
    @Size(min = 5, max = 20, message = "Username should be between 5 and 20 symbols")
    private String username;
    @NotNull(message = "Password can't be empty")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 symbols")
    private String password;
    @NotNull(message = "First name can't be empty")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 symbols")
    private String firstName;
    @NotNull(message = "Last name can't be empty")
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 symbols")
    private String lastName;
    @NotNull(message = "Email can't be empty")
    @Size(min = 5, max = 50, message = "Email should be between 5 and 50 symbols")
    private String email;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

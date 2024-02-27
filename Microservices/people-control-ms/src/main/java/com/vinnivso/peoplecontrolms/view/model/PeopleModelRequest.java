package com.vinnivso.peoplecontrolms.view.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PeopleModelRequest {
    @NotBlank(message = "Blank characters are not allowed")
    @NotEmpty(message = "The name needs to be informed")
    @Size(min = 5, message = "The name needs to be at least 5 characters long")
    private String name;

    @NotBlank(message = "Blank characters are not allowed")
    @NotEmpty(message = "The last name needs to be informed")
    private String lastName;

    //#region Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //#endregion Getters and Setters
}

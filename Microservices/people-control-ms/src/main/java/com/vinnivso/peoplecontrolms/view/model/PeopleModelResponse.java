package com.vinnivso.peoplecontrolms.view.model;

import java.util.UUID;

public class PeopleModelResponse {
    private UUID id;
    private String name;
    private String lastName;

    //#region Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

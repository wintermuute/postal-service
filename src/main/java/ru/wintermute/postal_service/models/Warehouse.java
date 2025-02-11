package ru.wintermute.postal_service.models;

import jakarta.persistence.Entity;

@Entity
public class Warehouse {
    private int id;
    private String name;
    private int postalCode;
    private String city;
    private String street;
    private int building;
    private String comment;

    public Warehouse(){

    }

    public Warehouse(String name, String comment, int building, String street, String city, int postalCode) {
        this.name = name;
        this.comment = comment;
        this.building = building;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package ru.wintermute.postal_service.models;

public class ServiceUser {
    private int id;
    private String name;
    //private PhoneNumber phoneNumber;
    private int postalCode;
    private String city;
    private String street;
    private int building;

    public ServiceUser() {
    }

    public ServiceUser(String name, int postalCode, String city, String street, int building) {
        this.name = name;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.building = building;
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
}

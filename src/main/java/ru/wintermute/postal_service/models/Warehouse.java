package ru.wintermute.postal_service.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "postalcode")
    private int postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private int building;
    @Column(name = "comment")
    private String comment;
    @OneToMany(mappedBy = "currentWarehouse", cascade = CascadeType.PERSIST)
    private List<Postage> postagesInStore;

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

    public List<Postage> getPostagesInStore() {
        return postagesInStore;
    }

    public void setPostagesInStore(List<Postage> postagesInStore) {
        this.postagesInStore = postagesInStore;
    }
}

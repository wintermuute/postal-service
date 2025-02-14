package ru.wintermute.postal_service.models;

import java.util.Date;

public class Postage {
    private int id;
    private int trackPrefix;
    private int trackNumber;
    private Date timeOfCreation;
    private Date timeArrived;
    private int senderId;
    private int receiverId;
    private Warehouse currentWarehouse;
    private double price;
    private Status status;
    private String comment;

    public Postage() {
    }

    public Postage(int trackPrefix, int trackNumber, Date timeOfCreation, Date timeArrived, double price, Status status, String comment) {
        this.trackPrefix = trackPrefix;
        this.trackNumber = trackNumber;
        this.timeOfCreation = timeOfCreation;
        this.timeArrived = timeArrived;
        this.price = price;
        this.status = status;
        this.comment = comment;
    }
}

package ru.wintermute.postal_service.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "postage")
public class Postage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trackprefix")
    private int trackPrefix;
    @Column(name = "tracknumber")
    private String trackNumber;
    @Column(name = "timeofcreation")
    //какого типа столбец в БД
    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeOfCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timearrived")
    private  LocalDateTime timeArrived;
    @Transient
    private int senderId;
    @Transient
    private int receiverId;
    @ManyToOne
    @JoinColumn(name = "currentwarehouse", referencedColumnName = "id")
    private Warehouse currentWarehouse;
    @Column(name = "weight")
    private double weight;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Column(name = "comment")
    private String comment;

    public Postage() {
    }

    public Postage(int trackPrefix, String trackNumber, LocalDateTime timeOfCreation, LocalDateTime timeArrived,double weight, double price, Status status, String comment) {
        this.trackPrefix = trackPrefix;
        this.trackNumber = trackNumber;
        this.timeOfCreation = timeOfCreation;
        this.timeArrived = timeArrived;
        this.weight = weight;
        this.price = price;
        this.status = status;
        this.comment = comment;
    }

    public void generateTrackNumber() {
        String trackNumber = "123456" + new Random().nextInt();
        setTrackNumber(trackNumber);

    }

    public void calculatePrice() {
        double price = this.weight * 0.5;
        this.setPrice(price);

    }
    public void detectCreationTime() {
        LocalDateTime stamp = LocalDateTime.now();
        this.setTimeOfCreation(stamp);
        if(this.status == Status.TAKEN) this.setTimeArrived(stamp);
    }
    public void resolveStatus(Status status) {
        switch (status) {
            case Status.ON_THE_WAY:
            case Status.LOST:
            case Status.RECEIVED:
                this.setCurrentWarehouse(null);
        }
    }
    public void detectTimeArrived() {
        LocalDateTime stamp = LocalDateTime.now();
        this.setTimeArrived(stamp);
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public double getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackPrefix() {
        return trackPrefix;
    }

    public void setTrackPrefix(int trackPrefix) {
        this.trackPrefix = trackPrefix;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public LocalDateTime getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(LocalDateTime timeArrived) {
        this.timeArrived = timeArrived;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public void setCurrentWarehouse(Warehouse currentWarehouse) {
        this.currentWarehouse = currentWarehouse;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}

package ru.wintermute.postal_service.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "postage")
public class Postage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trackPrefix")
    private int trackPrefix;
    @Column(name = "trackNumber")
    private String trackNumber;
    @Column(name = "dateOfCreation")
    //какого типа столбец в БД
    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timeOfCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timeArrived")
    private Date timeArrived;
    @Transient
    private int senderId;
    @Transient
    private int receiverId;

    private Warehouse currentWarehouse;
    @Column(name = "weight")
    private double weigth;
    @Column(name = "price")
    private double price;

    private Status status;
    @Column(name = "comment")
    private String comment;

    public Postage() {
    }

    public Postage(int trackPrefix, String trackNumber, Date timeOfCreation, Date timeArrived, double price, Status status, String comment) {
        this.trackPrefix = trackPrefix;
        this.trackNumber = trackNumber;
        this.timeOfCreation = timeOfCreation;
        this.timeArrived = timeArrived;
        this.price = price;
        this.status = status;
        this.comment = comment;
    }
}

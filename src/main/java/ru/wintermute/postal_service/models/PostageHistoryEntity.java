package ru.wintermute.postal_service.models;

import java.time.LocalDateTime;

public class PostageHistoryEntity {
    private String trackNumber;
    private LocalDateTime timeofcreation;
    private LocalDateTime timearrived;
    private String warehousename;
    private String status;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public LocalDateTime getTimearrived() {
        return timearrived;
    }

    public void setTimearrived(LocalDateTime timearrived) {
        this.timearrived = timearrived;
    }

    public LocalDateTime getTimeofcreation() {
        return timeofcreation;
    }

    public void setTimeofcreation(LocalDateTime timeofcreation) {
        this.timeofcreation = timeofcreation;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }
}

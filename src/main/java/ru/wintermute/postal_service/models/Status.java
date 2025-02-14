package ru.wintermute.postal_service.models;

public class Status {
    private int id;
    private String info;

    public Status() {
    }

    public Status(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

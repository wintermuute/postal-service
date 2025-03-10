package ru.wintermute.postal_service.models;

public enum Status {
    DUMMY("dummy"),
    NEW("новый") ,
    TAKEN("принят"),
    ON_THE_WAY("в пути"),
    IN_STORE("на складе"),
    WAITING_FOR_RECEIVER("ожидает получения"),
    RECEIVED("доставлен"),
    LOST("утерян");


    private String info;

    Status() {

    }

    Status(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

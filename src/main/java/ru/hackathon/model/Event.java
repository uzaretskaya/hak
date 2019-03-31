package ru.hackathon.model;

public class Event {

    private Long timeOpen;
    private Long timeClose;
    private String description;

    public Long getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Long timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Long getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Long timeClose) {
        this.timeClose = timeClose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

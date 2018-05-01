package com.example.taele.finalproject;

public class Item {
    private String name;
    private String photo;
    private String publisher;
    private String rId;

    public Item(String name, String photo, String publisher, String rId) {
        this.name = name;
        this.photo = photo;
        this.publisher = publisher;
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }
}

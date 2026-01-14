package com.demojava.practicingJava.model;

public class Testimonial {
    private String text;
    private String name;
    private String title;
    private String image;

    // Constructor
    public Testimonial(String text, String name, String title, String image) {
        this.text = text;
        this.name = name;
        this.title = title;
        this.image = image;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
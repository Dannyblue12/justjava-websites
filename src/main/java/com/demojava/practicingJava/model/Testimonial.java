package com.example.demo.model;

public class Testimonial {

    private String text;
    private String name;
    private String title;
    private String image;

    public Testimonial(String text, String name, String title, String image) {
        this.text = text;
        this.name = name;
        this.title = title;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}

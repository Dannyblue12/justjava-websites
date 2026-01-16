package com.demojava.practicingJava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;     // Holds the text (e.g., "We help start-ups...")
    private String authorName;  // Holds "HERO_CONTENT" (if it's the hero) or "Simon Claw" (if it's a review)
    private String title;
    private String image;

    public Testimonial() {
    }

    public Testimonial(String content, String authorName, String title, String image) {
        this.content = content;
        this.authorName = authorName;
        this.title = title;
        this.image = image;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
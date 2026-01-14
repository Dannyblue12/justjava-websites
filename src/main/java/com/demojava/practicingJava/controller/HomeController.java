package com.demojava.practicingJava.controller;

// CORRECTION HERE: We add ".model" to tell Java to look in the model folder
import com.demojava.practicingJava.model.Testimonial;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Testimonial> testimonials = List.of(
                new Testimonial(
                        "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline.",
                        "Simon Claw",
                        "CEO & Co-Founder",
                        "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
                ),
                new Testimonial(
                        "JustJava rebuilt our backend and delivered on time and i really enjoy using their services a lot",
                        "Sarah White",
                        "Product Manager",
                        "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
                ),
                new Testimonial(
                        "Communication was clear, delivery was solid.",
                        "Michael Doe",
                        "CTO",
                        "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
                )
        );

        model.addAttribute("testimonials", testimonials);
        return "index";
    }

    @PostMapping("/save-testimonial")
    public String saveTestimonial(
            @RequestParam(name = "authorName", required = false) String authorName,
            @RequestParam("content") String newContent
    ) {
        System.out.println("===== FORM SUBMITTED =====");
        System.out.println("Author: " + (authorName != null ? authorName : "Hero Section"));
        System.out.println("New Content: " + newContent);
        System.out.println("==========================");

        return "redirect:/";
    }
}
package com.demojava.practicingJava.controller;

import com.example.demo.model.Testimonial;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        List<Testimonial> testimonials = List.of(
                new Testimonial(
                        "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team",
                        "Simon Claw",
                        "CEO & Co-Founder",
                        "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
                ),
                new Testimonial(
                        "JustJava rebuilt our backend and delivered on time and i really enjoy using their services a lot",
                        "Sarah White",
                        "Product Manager",
                        "sarah.jpg"
                ),
                new Testimonial(
                        "Communication was clear, delivery was solid.",
                        "Michael Doe",
                        "CTO",
                        "michael.jpg"
                )
        );

        model.addAttribute("testimonials", testimonials);

        return "index";
    }
}

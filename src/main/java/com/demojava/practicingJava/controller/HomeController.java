package com.demojava.practicingJava.controller;

import com.demojava.practicingJava.model.Testimonial;
import com.demojava.practicingJava.repository.TestimonialRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    private final TestimonialRepository testimonialRepository;

    public HomeController(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        // 1. GET HERO TEXT
        // We look for a special row where authorName is "HERO_CONTENT"
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");

        // If it doesn't exist (first time running), create it
        if (heroText == null) {
            heroText = new Testimonial(
                    "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process.",
                    "HERO_CONTENT", // <--- THIS IS THE FLAG
                    "System",
                    "default.jpg"
            );
            testimonialRepository.save(heroText);
        }
        model.addAttribute("heroText", heroText);


        // 2. GET TESTIMONIALS (GRID)
        // Fetch everything EXCEPT the Hero Content row so it doesn't show up in the grid
        List<Testimonial> testimonials = testimonialRepository.findByAuthorNameNot("HERO_CONTENT");
        model.addAttribute("testimonials", testimonials);

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // --- 1. SAVE TESTIMONIALS (Updates existing ones using ID) ---
    @PostMapping("/save-testimonial")
    @ResponseBody
    public String saveTestimonial(
            @RequestParam(name = "id") Long id, // <--- Required to find the right row
            @RequestParam("content") String newContent
    ) {
        // Find the existing row in the database
        Testimonial t = testimonialRepository.findById(id).orElse(null);

        if (t != null) {
            // Update the content (keep image/name the same)
            t.setContent(newContent);
            testimonialRepository.save(t); // This performs an UPDATE
            return "Updated Successfully";
        }

        return "Error: Testimonial not found";
    }

    // --- 2. SAVE HERO TEXT (Finds the special "HERO_CONTENT" row) ---
    @PostMapping("/save-hero")
    @ResponseBody
    public String saveHero(@RequestParam("content") String newContent) {
        // Find the specific Hero row
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");

        if (heroText != null) {
            heroText.setContent(newContent);
            testimonialRepository.save(heroText);
            return "Saved Hero Text";
        }

        return "Error: Hero text not found";
    }
}
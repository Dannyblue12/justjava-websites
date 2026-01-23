package com.demojava.practicingJava.controller;

import com.demojava.practicingJava.model.Testimonial;
import com.demojava.practicingJava.repository.TestimonialRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final TestimonialRepository testimonialRepository;

    public HomeController(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }


    @GetMapping({"/", "/public"})
    public String publicPage(Model model) {
        // Load the data (Hero text + Grid)
        loadPageData(model);

        // Turn EDIT MODE -> OFF
        model.addAttribute("editMode", false);

        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        // Load the same data
        loadPageData(model);

        // Turn EDIT MODE -> ON
        model.addAttribute("editMode", true);

        // Get the logged-in user's name (e.g., "Daniel")
        if (principal != null) {
            String name = principal.getAttribute("name"); // Keycloak usually sends 'name' or 'preferred_username'
            model.addAttribute("username", name != null ? name : "Admin");
        }

        return "index";
    }


    @PostMapping("/admin/save-testimonial")
    public String saveTestimonial(
            @RequestParam(name = "id") Long id,
            @RequestParam("content") String newContent
    ) {
        Testimonial t = testimonialRepository.findById(id).orElse(null);

        if (t != null) {
            t.setContent(newContent);
            testimonialRepository.save(t);
        }
        // Redirect back to the Admin page to see changes immediately
        return "redirect:/admin";
    }

    @PostMapping("/admin/save-hero")
    public String saveHero(@RequestParam("content") String newContent) {
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");

        if (heroText != null) {
            heroText.setContent(newContent);
            testimonialRepository.save(heroText);
        }
        // Redirect back to the Admin page
        return "redirect:/admin";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    private void loadPageData(Model model) {
        // A. GET HERO TEXT
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");

        // First time setup: Create it if missing
        if (heroText == null) {
            heroText = new Testimonial(
                    "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process.",
                    "HERO_CONTENT",
                    "System",
                    "default.jpg"
            );
            testimonialRepository.save(heroText);
        }
        model.addAttribute("heroText", heroText);

        // B. GET TESTIMONIALS (GRID)
        // Exclude the Hero Content row
        List<Testimonial> testimonials = testimonialRepository.findByAuthorNameNot("HERO_CONTENT");
        model.addAttribute("testimonials", testimonials);
    }
}
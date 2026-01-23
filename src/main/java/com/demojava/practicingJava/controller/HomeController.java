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

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final TestimonialRepository testimonialRepository;

    public HomeController(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @GetMapping({"/", "/public"})
    public String publicPage(Model model) {
        loadPageData(model);
        model.addAttribute("editMode", false);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        loadPageData(model);
        model.addAttribute("editMode", true);

        if (principal != null) {
            String name = principal.getAttribute("name");
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
        return "redirect:/admin";
    }

    @PostMapping("/admin/save-hero")
    public String saveHero(@RequestParam("content") String newContent) {
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");
        if (heroText != null) {
            heroText.setContent(newContent);
            testimonialRepository.save(heroText);
        }
        return "redirect:/admin";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    private void loadPageData(Model model) {
        // --- A. HERO TEXT SETUP ---
        Testimonial heroText = testimonialRepository.findByAuthorName("HERO_CONTENT");
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

        // --- B. TESTIMONIAL GRID SETUP ---
        List<Testimonial> testimonials = testimonialRepository.findByAuthorNameNot("HERO_CONTENT");

        // IF THE GRID IS EMPTY, CREATE DEFAULT CARDS
        if (testimonials.isEmpty()) {
            List<Testimonial> defaults = new ArrayList<>();

            defaults.add(new Testimonial(
                    "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline.",
                    "Simon Claw",
                    "CEO & Co-Founder",
                    "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
            ));

            defaults.add(new Testimonial(
                    "JustJava rebuilt our backend and delivered on time and i really enjoy using their services a lot",
                    "Sarah White",
                    "Product Manager",
                    "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
            ));

            defaults.add(new Testimonial(
                    "Communication was clear, delivery was solid.",
                    "Michael Doe",
                    "CTO",
                    "32603aa2bb5a605bdf4f394aa9dbfdb440bdd68e.jpg"
            ));

            
            testimonialRepository.saveAll(defaults);
            
            
            testimonials = defaults;
        }

        model.addAttribute("testimonials", testimonials);
    }
}

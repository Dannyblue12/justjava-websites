package com.demojava.practicingJava.repository;

import com.demojava.practicingJava.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    // 1. Find the specific Hero row
    Testimonial findByAuthorName(String authorName);

    // 2. Find everything EXCEPT the Hero row (for the grid)
    List<Testimonial> findByAuthorNameNot(String authorName);
}
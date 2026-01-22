package com.demojava.practicingJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration; 

// The 'exclude' part below guarantees NO login screen, even if a dependency sneaks in.
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PracticingJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticingJavaApplication.class, args);
    }
}

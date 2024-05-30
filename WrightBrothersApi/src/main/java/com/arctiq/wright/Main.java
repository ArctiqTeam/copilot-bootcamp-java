package com.arctiq.wright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import java.util.Random;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public RedirectView redirectRootToSwagger() {
        return new RedirectView("/swagger-ui/index.html");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        boolean systemPerformance = checkSystemPerformance();
        if (systemPerformance) {
            return ResponseEntity.status(200).body("The system is performing optimally.");
        } else {
            boolean minorIssue = checkIfMinorIssue();
            if (minorIssue) {
                return ResponseEntity.status(200).body("The system is experiencing a minor issue.");
            } else {
                return ResponseEntity.status(500).body("The system is experiencing a major issue.");
            }
        }
    }

    public boolean checkSystemPerformance() {
        Random random = new Random();
        return random.nextInt(100) > 10;
    }

    public boolean checkIfMinorIssue() {
        Random random = new Random();
        return random.nextInt(100) > 50;
    }
}
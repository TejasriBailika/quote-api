package com.example.quote_api.controller;

import com.example.quote_api.model.Quote;
import com.example.quote_api.service.RateLimitingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class QuoteController {
    private final RateLimitingService rateLimitingService;
    private final List<String> quotes = Arrays.asList(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Innovation distinguishes between a leader and a follower. - Steve Jobs",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The way to get started is to quit talking and begin doing. - Walt Disney",
            "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success. - James Cameron",
            "You may say I'm a dreamer, but I'm not the only one. - John Lennon",
            "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
            "Life is what happens when you're busy making other plans. - John Lennon",
            "Spread love everywhere you go. - Mother Teresa",
            "The only thing we have to fear is fear itself. - Franklin D. Roosevelt"
    );

    public QuoteController(RateLimitingService rateLimitingService) {
        this.rateLimitingService = rateLimitingService;
    }

    @GetMapping("/quote")
    public ResponseEntity<Object> getRandomQuote(HttpServletRequest request) {
        String clientIp = getClientIp(request);

        if (!rateLimitingService.isAllowed(clientIp)) {
            long waitTime = rateLimitingService.getTimeUntilReset(clientIp);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("{\"error\": \"Rate limit exceeded. Try again in " + waitTime + " seconds.\"}");
        }

        Random random = new Random();
        String randomQuote = quotes.get(random.nextInt(quotes.size()));

        return ResponseEntity.ok(new Quote(randomQuote));
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }
}
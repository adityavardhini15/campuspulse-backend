package com.campuspulse.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SentimentAnalysisService {

    private static final List<String> POSITIVE_WORDS = Arrays.asList(
            "great", "excellent", "good", "love", "amazing", "wonderful", 
            "best", "fantastic", "nice", "helpful", "clean", "perfect", 
            "happy", "satisfied"
    );

    private static final List<String> NEGATIVE_WORDS = Arrays.asList(
            "bad", "poor", "terrible", "awful", "worst", "horrible", 
            "disappointing", "useless", "dirty", "slow", "broken", 
            "waste", "unhappy"
    );

    public String analyzeSentiment(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "NEUTRAL";
        }

        String lowerText = text.toLowerCase();
        
        long positiveCount = POSITIVE_WORDS.stream()
                .filter(lowerText::contains)
                .count();
        
        long negativeCount = NEGATIVE_WORDS.stream()
                .filter(lowerText::contains)
                .count();

        int score = (int) (positiveCount - negativeCount);

        if (score > 0) {
            return "POSITIVE";
        } else if (score < 0) {
            return "NEGATIVE";
        } else {
            return "NEUTRAL";
        }
    }
}

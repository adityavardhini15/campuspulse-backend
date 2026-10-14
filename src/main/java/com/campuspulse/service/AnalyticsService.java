package com.campuspulse.service;

import com.campuspulse.dto.response.AnalyticsResponse;
import com.campuspulse.entity.Feedback;
import com.campuspulse.entity.Service;
import com.campuspulse.repository.FeedbackRepository;
import com.campuspulse.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class AnalyticsService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public AnalyticsResponse getOverview() {
        Long totalFeedback = feedbackRepository.count();
        Double averageRating = feedbackRepository.findAverageRating();
        
        Long positiveCount = feedbackRepository.countBySentiment("POSITIVE");
        Long neutralCount = feedbackRepository.countBySentiment("NEUTRAL");
        Long negativeCount = feedbackRepository.countBySentiment("NEGATIVE");

        if (averageRating == null) {
            averageRating = 0.0;
        }

        return new AnalyticsResponse(
                totalFeedback,
                averageRating,
                positiveCount,
                neutralCount,
                negativeCount
        );
    }

    public List<Map<String, Object>> getServiceRatings() {
        List<Service> services = serviceRepository.findAll();
        
        return services.stream().map(service -> {
            Map<String, Object> serviceRating = new HashMap<>();
            serviceRating.put("serviceName", service.getName());
            
            Double avgRating = feedbackRepository.findAverageRatingByServiceId(service.getId());
            serviceRating.put("averageRating", avgRating != null ? avgRating : 0.0);
            
            return serviceRating;
        }).collect(Collectors.toList());
    }

    public Map<String, Map<String, Long>> getSentimentBreakdown() {
        List<Service> services = serviceRepository.findAll();
        List<Feedback> allFeedback = feedbackRepository.findAll();
        
        Map<String, Map<String, Long>> sentimentByService = new HashMap<>();
        
        for (Service service : services) {
            Map<String, Long> sentiments = new HashMap<>();
            sentiments.put("POSITIVE", 0L);
            sentiments.put("NEGATIVE", 0L);
            sentiments.put("NEUTRAL", 0L);
            
            List<Feedback> serviceFeedback = allFeedback.stream()
                    .filter(f -> f.getService().getId().equals(service.getId()))
                    .collect(Collectors.toList());
            
            for (Feedback feedback : serviceFeedback) {
                String sentiment = feedback.getSentiment();
                sentiments.put(sentiment, sentiments.get(sentiment) + 1);
            }
            
            sentimentByService.put(service.getName(), sentiments);
        }
        
        return sentimentByService;
    }

    public List<Map<String, Object>> getTrends() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Feedback> recentFeedback = feedbackRepository.findAll().stream()
                .filter(f -> f.getCreatedAt().isAfter(sevenDaysAgo))
                .collect(Collectors.toList());

        Map<LocalDate, Long> feedbackByDate = recentFeedback.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getCreatedAt().toLocalDate(),
                        Collectors.counting()
                ));

        return feedbackByDate.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> trend = new HashMap<>();
                    trend.put("date", entry.getKey().toString());
                    trend.put("count", entry.getValue());
                    return trend;
                })
                .sorted((a, b) -> ((String) a.get("date")).compareTo((String) b.get("date")))
                .collect(Collectors.toList());
    }
}

package com.campuspulse.controller;

import com.campuspulse.dto.response.AnalyticsResponse;
import com.campuspulse.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/overview")
    public ResponseEntity<AnalyticsResponse> getOverview() {
        AnalyticsResponse response = analyticsService.getOverview();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/service-ratings")
    public ResponseEntity<List<Map<String, Object>>> getServiceRatings() {
        List<Map<String, Object>> ratings = analyticsService.getServiceRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/sentiment")
    public ResponseEntity<Map<String, Map<String, Long>>> getSentimentBreakdown() {
        Map<String, Map<String, Long>> sentiment = analyticsService.getSentimentBreakdown();
        return ResponseEntity.ok(sentiment);
    }

    @GetMapping("/trends")
    public ResponseEntity<List<Map<String, Object>>> getTrends() {
        List<Map<String, Object>> trends = analyticsService.getTrends();
        return ResponseEntity.ok(trends);
    }
}

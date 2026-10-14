package com.campuspulse.controller;

import com.campuspulse.dto.request.FeedbackRequest;
import com.campuspulse.dto.response.FeedbackResponse;
import com.campuspulse.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(
            @Valid @RequestBody FeedbackRequest request,
            Authentication authentication) {
        String username = authentication.getName();
        FeedbackResponse response = feedbackService.submitFeedback(request, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedback() {
        List<FeedbackResponse> feedback = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/user")
    public ResponseEntity<List<FeedbackResponse>> getUserFeedback(Authentication authentication) {
        String username = authentication.getName();
        List<FeedbackResponse> feedback = feedbackService.getUserFeedback(username);
        return ResponseEntity.ok(feedback);
    }
}

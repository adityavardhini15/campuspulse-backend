package com.campuspulse.service;

import com.campuspulse.dto.request.FeedbackRequest;
import com.campuspulse.dto.response.FeedbackResponse;
import com.campuspulse.entity.Feedback;
import com.campuspulse.entity.Service;
import com.campuspulse.entity.User;
import com.campuspulse.exception.ResourceNotFoundException;
import com.campuspulse.repository.FeedbackRepository;
import com.campuspulse.repository.ServiceRepository;
import com.campuspulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    public FeedbackResponse submitFeedback(FeedbackRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        String sentiment = sentimentAnalysisService.analyzeSentiment(request.getComment());

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setService(service);
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());
        feedback.setSentiment(sentiment);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return toFeedbackResponse(savedFeedback);
    }

    public List<FeedbackResponse> getAllFeedback() {
        return feedbackRepository.findAll().stream()
                .map(this::toFeedbackResponse)
                .collect(Collectors.toList());
    }

    public List<FeedbackResponse> getUserFeedback(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return feedbackRepository.findByUserId(user.getId()).stream()
                .map(this::toFeedbackResponse)
                .collect(Collectors.toList());
    }

    private FeedbackResponse toFeedbackResponse(Feedback feedback) {
        return new FeedbackResponse(
                feedback.getId(),
                feedback.getUser().getUsername(),
                feedback.getService().getName(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getSentiment(),
                feedback.getCreatedAt()
        );
    }
}

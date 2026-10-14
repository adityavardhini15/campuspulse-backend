package com.campuspulse.repository;

import com.campuspulse.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    List<Feedback> findByUserId(Long userId);
    
    List<Feedback> findByServiceId(Long serviceId);
    
    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.sentiment = ?1")
    Long countBySentiment(String sentiment);
    
    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double findAverageRating();
    
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.service.id = ?1")
    Double findAverageRatingByServiceId(Long serviceId);
}

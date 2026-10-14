package com.campuspulse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private Long id;
    private String username;
    private String serviceName;
    private Integer rating;
    private String comment;
    private String sentiment;
    private LocalDateTime createdAt;
}

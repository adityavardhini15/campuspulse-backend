package com.campuspulse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResponse {
    private Long totalFeedback;
    private Double averageRating;
    private Long positiveCount;
    private Long neutralCount;
    private Long negativeCount;
}

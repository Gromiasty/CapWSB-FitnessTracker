package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for updating Training details.
 */
@AllArgsConstructor
@Getter
public class TrainingUpdate {
    
    /**
     * The ID of the user associated with the training session.
     */
    private final Long userId;
    
    /**
     * The start time of the training session.
     */
    private final LocalDateTime startTime;
    
    /**
     * The end time of the training session.
     */
    private final LocalDateTime endTime;
    
    /**
     * The type of activity performed during the training session.
     */
    private final String activityType;
    
    /**
     * The distance covered during the training session in kilometers.
     */
    private final double distance;
    
    /**
     * The average speed during the training session in kilometers per hour.
     */
    private final double averageSpeed;
}
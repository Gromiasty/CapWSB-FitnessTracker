package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Training entities.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of training sessions for the specified user
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds all training sessions that were completed after a specific time.
     *
     * @param endTime the end time to compare
     * @return a list of training sessions completed after the specified time
     */
    List<Training> findByEndTimeAfter(LocalDateTime endTime);

    /**
     * Finds all training sessions of a specific activity type.
     *
     * @param activityType the type of activity
     * @return a list of training sessions of the specified activity type
     */
    List<Training> findByActivityType(ActivityType activityType);

    List<Training> findByUserIdAndStartTimeBetween(Long id, LocalDateTime startOfWeek, LocalDateTime endOfWeek);
}
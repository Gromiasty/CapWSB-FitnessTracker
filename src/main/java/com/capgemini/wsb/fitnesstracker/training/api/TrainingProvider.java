package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface for providing training-related operations.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on its ID.
     *
     * @param trainingId the ID of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves trainings for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of trainings for the specified user
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves trainings that were completed after a specific date.
     *
     * @param date the date to compare
     * @return a list of trainings completed after the specified date
     */
    List<Training> getCompletedTrainingsAfter(LocalDate date);

    /**
     * Retrieves trainings of a specific activity type.
     *
     * @param activityType the type of activity
     * @return a list of trainings of the specified activity type
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);
}
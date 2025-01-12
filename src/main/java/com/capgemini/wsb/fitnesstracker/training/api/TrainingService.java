package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingUpdate;

/**
 * Interface for managing training-related operations.
 */
public interface TrainingService {

    /**
     * Creates a new training session.
     *
     * @param training the data for the new training session
     * @return the created training session
     */
    Training createTraining(TrainingUpdate training);

    /**
     * Updates an existing training session.
     *
     * @param id the ID of the training session to be updated
     * @param training the updated data for the training session
     * @return the updated training session
     */
    Training updateTraining(Long id, TrainingUpdate training);
}
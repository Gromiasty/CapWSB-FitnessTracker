package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert Training entities to TrainingDto.
 */
@Component
@AllArgsConstructor
class TrainingMapper {

    /**
     * Converts a Training entity to a TrainingDto.
     *
     * @param training the Training entity to convert
     * @return the converted TrainingDto
     */
    TrainingDto toTrainingDto(Training training) {
        if (training == null) {
            throw new IllegalArgumentException("Training cannot be null");
        }

        if (training.getUser() == null) {
            throw new IllegalArgumentException("User in Training cannot be null");
        }

        TrainingUserDto userDto = new TrainingUserDto(
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getEmail()
        );

        return new TrainingDto(
                training.getId(),
                userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getDistance(),
                training.getAverageSpeed(),
                training.getActivityType()
        );
    }
}
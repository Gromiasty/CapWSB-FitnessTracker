package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        log.info("Fetching training with ID: {}", trainingId);
        return trainingRepository.findById(trainingId)
                .or(() -> {
                    log.warn("Training with ID {} not found", trainingId);
                    return Optional.empty();
                });
    }

    @Override
    public List<Training> getAllTrainings() {
        log.info("Fetching all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        log.info("Fetching trainings for user ID: {}", userId);
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getCompletedTrainingsAfter(LocalDate date) {
        log.info("Fetching completed trainings after: {}", date);
        return trainingRepository.findByEndTimeAfter(date.atStartOfDay());
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        log.info("Fetching trainings with activity type: {}", activityType);
        return trainingRepository.findByActivityType(activityType);
    }

    @Transactional
    @Override
    public Training createTraining(TrainingUpdate training) {
        log.info("Creating new training for user ID: {}", training.getUserId());
        User user = getUserById(training.getUserId());

        Training newTraining = buildTrainingFromUpdate(user, training);
        return trainingRepository.save(newTraining);
    }

    @Transactional
    @Override
    public Training updateTraining(Long id, TrainingUpdate training) {
        log.info("Updating training with ID: {}", id);
        return trainingRepository.findById(id)
                .map(existingTraining -> {
                    User user = getUserById(training.getUserId());
                    updateTrainingDetails(existingTraining, user, training);
                    return trainingRepository.save(existingTraining);
                })
                .orElseThrow(() -> new TrainingNotFoundException(id));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Training buildTrainingFromUpdate(User user, TrainingUpdate training) {
        return new Training(
                user,
                training.getStartTime(),
                training.getEndTime(),
                ActivityType.valueOf(training.getActivityType()),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    private void updateTrainingDetails(Training existingTraining, User user, TrainingUpdate training) {
        existingTraining.setUser(user);
        existingTraining.setStartTime(training.getStartTime());
        existingTraining.setEndTime(training.getEndTime());
        existingTraining.setActivityType(ActivityType.valueOf(training.getActivityType()));
        existingTraining.setDistance(training.getDistance());
        existingTraining.setAverageSpeed(training.getAverageSpeed());
    }
}
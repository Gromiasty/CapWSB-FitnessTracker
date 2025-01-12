package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
class TrainingController {

    private final TrainingProvider trainingProvider;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        log.info("Fetching all trainings");
        List<TrainingDto> trainings = trainingProvider.getAllTrainings().stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsByUser(@PathVariable Long userId) {
        log.info("Fetching trainings for user ID: {}", userId);
        List<TrainingDto> trainings = trainingProvider.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingUpdate training) {
        log.info("Creating new training for user ID: {}", training.getUserId());
        TrainingDto createdTraining = trainingMapper.toTrainingDto(trainingService.createTraining(training));
        return ResponseEntity.status(CREATED).body(createdTraining);
    }

    @GetMapping("/completed/{date}")
    public ResponseEntity<List<TrainingDto>> getCompletedTrainingsAfter(@PathVariable LocalDate date) {
        log.info("Fetching completed trainings after: {}", date);
        List<TrainingDto> trainings = trainingProvider.getCompletedTrainingsAfter(date).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        log.info("Fetching trainings with activity type: {}", activityType);
        List<TrainingDto> trainings = trainingProvider.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @RequestBody TrainingUpdate training) {
        log.info("Updating training with ID: {}", id);
        TrainingDto updatedTraining = trainingMapper.toTrainingDto(trainingService.updateTraining(id, training));
        return ResponseEntity.ok(updatedTraining);
    }

    @ExceptionHandler({UserNotFoundException.class, TrainingNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        log.warn("Exception: {}", e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
}
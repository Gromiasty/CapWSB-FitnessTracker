package com.capgemini.wsb.fitnesstracker.service;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private TrainingRepository trainingRepository;

    public String generateWeeklyReport(User user) {
        LocalDateTime startOfWeek = LocalDateTime.now().minusWeeks(1);
        LocalDateTime endOfWeek = LocalDateTime.now();
        List<Training> trainings = trainingRepository.findByUserIdAndStartTimeBetween(user.getId(), startOfWeek, endOfWeek);

        StringBuilder report = new StringBuilder();
        report.append("Weekly Report for ").append(user.getLastName()).append("\n\n");
        report.append("Total Trainings: ").append(trainings.size()).append("\n");

        for (Training training : trainings) {
            report.append(training.getActivityType()).append(": ")
                  .append(training.getDistance()).append(" km, ")
                  .append(training.getAverageSpeed()).append(" km/h\n");
        }

        return report.toString();
    }
}
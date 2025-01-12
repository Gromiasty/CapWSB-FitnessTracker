package com.capgemini.wsb.fitnesstracker.statistics.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing statistics.
 */
@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @SuppressWarnings("null")
    public Statistics createStatistics(StatisticsDTO statisticsDTO) {
        Statistics statistics = new Statistics();
        statistics.setUserId(statisticsDTO.getUserId());
        statistics.setCalories(statisticsDTO.getCalories());
        statistics.setSteps(statisticsDTO.getSteps());
        statistics.setDistance(statisticsDTO.getDistance());
        return statisticsRepository.save(statistics);
    }

    @SuppressWarnings("null")
    public Statistics updateStatistics(Long id, StatisticsDTO statisticsDTO) {
        Optional<Statistics> optionalStatistics = statisticsRepository.findById(id);
        if (optionalStatistics.isPresent()) {
            Statistics statistics = optionalStatistics.get();
            statistics.setCalories(statisticsDTO.getCalories());
            statistics.setSteps(statisticsDTO.getSteps());
            statistics.setDistance(statisticsDTO.getDistance());
            return statisticsRepository.save(statistics);
        } else {
            throw new RuntimeException("Statistics not found");
        }
    }

    @SuppressWarnings("null")
    public Statistics getStatisticsById(Long id) {
        return statisticsRepository.findById(id).orElseThrow(() -> new RuntimeException("Statistics not found"));
    }

    public void deleteStatistics(Long id) {
        statisticsRepository.deleteById(id);
    }

    public List<Statistics> findAllStatisticsByCaloriesGreaterThan(int calories) {
        return statisticsRepository.findByCaloriesGreaterThan(calories);
    }
}
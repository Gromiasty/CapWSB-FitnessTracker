package com.capgemini.wsb.fitnesstracker.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsRepository;

/**
 * Data loader to initialize sample data.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create sample statistics
        Statistics statistics1 = new Statistics();
        statistics1.setUserId(1L);
        statistics1.setCalories(500);
        statistics1.setSteps(10000);
        statistics1.setDistance(5);
        statisticsRepository.save(statistics1);

        Statistics statistics2 = new Statistics();
        statistics2.setUserId(2L);
        statistics2.setCalories(300);
        statistics2.setSteps(7000);
        statistics2.setDistance(3);
        statisticsRepository.save(statistics2);
    }
}
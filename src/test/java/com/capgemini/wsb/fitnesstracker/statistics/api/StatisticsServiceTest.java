package com.capgemini.wsb.fitnesstracker.statistics.api;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsRepository;

import java.util.Optional;

public class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setUserId(1L);
        statisticsDTO.setCalories(500);
        statisticsDTO.setSteps(10000);
        statisticsDTO.setDistance(5);

        Statistics statistics = new Statistics();
        statistics.setId(1L);
        statistics.setUserId(1L);
        statistics.setCalories(500);
        statistics.setSteps(10000);
        statistics.setDistance(5);

        when(statisticsRepository.save(any(Statistics.class))).thenReturn(statistics);

        Statistics createdStatistics = statisticsService.createStatistics(statisticsDTO);

        assertNotNull(createdStatistics);
        assertEquals(1L, createdStatistics.getId());
        assertEquals(500, createdStatistics.getCalories());
    }

    @Test
    public void testUpdateStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setCalories(600);
        statisticsDTO.setSteps(12000);
        statisticsDTO.setDistance(6);

        Statistics existingStatistics = new Statistics();
        existingStatistics.setId(1L);
        existingStatistics.setUserId(1L);
        existingStatistics.setCalories(500);
        existingStatistics.setSteps(10000);
        existingStatistics.setDistance(5);

        when(statisticsRepository.findById(1L)).thenReturn(Optional.of(existingStatistics));
        when(statisticsRepository.save(any(Statistics.class))).thenReturn(existingStatistics);

        Statistics updatedStatistics = statisticsService.updateStatistics(1L, statisticsDTO);

        assertNotNull(updatedStatistics);
        assertEquals(600, updatedStatistics.getCalories());
        assertEquals(12000, updatedStatistics.getSteps());
    }
}
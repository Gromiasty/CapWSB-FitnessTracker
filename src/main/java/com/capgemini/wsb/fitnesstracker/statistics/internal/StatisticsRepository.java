package com.capgemini.wsb.fitnesstracker.statistics.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import java.util.List;

/**
 * Repository for Statistics entity.
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    List<Statistics> findByCaloriesGreaterThan(int calories);
}
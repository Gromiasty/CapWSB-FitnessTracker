package com.capgemini.wsb.fitnesstracker.scheduler;

import com.capgemini.wsb.fitnesstracker.service.EmailReportService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportScheduler {

    @Autowired
    private EmailReportService emailReportService;
    @Autowired
    private UserProvider userProvider;

    // Schedule the task to run every Sunday at midnight
    @Scheduled(cron = "0 0 0 * * SUN")
    public void scheduleReportJob() {
        List<User> users = userProvider.findAllUsers();
        for (User user : users) {
            emailReportService.sendWeeklyReport(user);
        }
    }
}
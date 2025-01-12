package com.capgemini.wsb.fitnesstracker.service;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailReportService {
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ReportService reportService;

    public void sendWeeklyReport(User user) {
        String report = reportService.generateWeeklyReport(user);
        EmailDto email = new EmailDto(user.getEmail(), "Weekly Training Report", report);
        emailSender.send(email);
    }
}
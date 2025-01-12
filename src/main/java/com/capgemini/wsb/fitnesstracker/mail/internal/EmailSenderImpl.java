package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    @Override
    public void send(@SuppressWarnings("null") EmailDto email) {
        // Implement the logic to send the email
        // This can be done using JavaMailSender or any other email sending library
        System.out.println("Sending email to: " + email.getToAddress());
        System.out.println("Subject: " + email.getSubject());
        System.out.println("Content: " + email.getContent());
    }
}
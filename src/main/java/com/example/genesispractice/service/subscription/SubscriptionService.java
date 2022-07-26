package com.example.genesispractice.service.subscription;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private static final String FILENAME = "/home/vitalik/Documents/Emails.txt";

    private final JavaMailSender mailSender;

    public SubscriptionService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public synchronized void addSubscriber(String email) {
        try (PrintWriter bufferedWriter = new PrintWriter(new FileWriter(FILENAME), true)) {
            bufferedWriter.println(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mlangbardasdfshrth@gmail.com");
        message.setTo("vitalikkryvonosiuk@gmail.com");
        message.setText(email);
        mailSender.send(message);
    }
}

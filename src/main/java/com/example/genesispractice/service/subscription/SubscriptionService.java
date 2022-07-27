package com.example.genesispractice.service.subscription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final File file = new File("C:\\Users\\START\\Documents\\Emails.txt");

    private final JavaMailSender mailSender;

    @SneakyThrows
    public SubscriptionService(JavaMailSender mailSender){
        this.mailSender = mailSender;
        file.createNewFile();
    }

    public synchronized void addSubscriber(String email) {
        try (PrintWriter bufferedWriter = new PrintWriter(new FileWriter(file, true))) {
            verifyIfEmailExists(email, file);
            bufferedWriter.println(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMail(String emailText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mlangbardasdfshrth@gmail.com");
        message.setText(emailText);

        try {
            Files.lines(file.toPath()).forEach(line -> sendEmail(line.trim(), message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void verifyIfEmailExists(String email, File file) throws IOException {
        Files.lines(file.toPath()).forEach(line -> throwIfLineContainsEmail(line, email));
    }

    private void throwIfLineContainsEmail(String line, String email) {
        if(line.contains(email.trim())) {
            throw new IllegalArgumentException("Email already exists"); //TODO proper exception
        }
    }

    private void sendEmail(String address, SimpleMailMessage message) {
        message.setTo(address);
        mailSender.send(message);
    }
}

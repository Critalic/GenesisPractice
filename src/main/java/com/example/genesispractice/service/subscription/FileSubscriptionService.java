package com.example.genesispractice.service.subscription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("FileService")
public class FileSubscriptionService implements SubscriptionService {

    private final File file = new File("/home/vitalik/Documents/Emails.txt");

    private final JavaMailSender mailSender;

    @SneakyThrows
    public FileSubscriptionService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        file.createNewFile();
    }

    public synchronized void addSubscriber(String email) throws IOException {
        try (PrintWriter bufferedWriter = new PrintWriter(new FileWriter(file, true))) {
            verifyIfEmailExists(email, file);
            bufferedWriter.println(email);
        }
    }

    public void sendMail(String emailText) throws IOException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mlangbardasdfshrth@gmail.com");
        message.setText(emailText);

        try(Stream<String> emailStream = Files.lines(file.toPath())) {
            emailStream.forEach(line -> sendEmail(line.trim(), message));
        }
    }

    private void verifyIfEmailExists(String email, File file) throws IOException {
        try(Stream<String> emailStream = Files.lines(file.toPath())) {
            emailStream.forEach(line -> throwIfLineContainsEmail(line, email));
        }
    }

    private void throwIfLineContainsEmail(String line, String email) {
        if (line.contains(email.trim())) {
            throw new IllegalArgumentException("Email already exists"); //TODO proper exception
        }
    }

    private void sendEmail(String address, SimpleMailMessage message) {
        message.setTo(address);
        mailSender.send(message);
    }
}

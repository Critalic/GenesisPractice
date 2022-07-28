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

    public synchronized boolean addSubscriber(String email) throws IOException {
        if (emailAlreadyExists(email, file)) {
            return false;
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.println(email);
        }

        return true;
    }

    public void sendMail(String emailText) throws IOException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(emailText);

        try (Stream<String> emailStream = Files.lines(file.toPath())) {
            emailStream.forEach(line -> sendEmail(line.trim(), message));
        }
    }

    private boolean emailAlreadyExists(String email, File file) throws IOException {
        try (Stream<String> emailStream = Files.lines(file.toPath())) {
            return emailStream
                .anyMatch(line -> line.trim().equals(email.trim()));
        }
    }

    private void sendEmail(String address, SimpleMailMessage message) {
        message.setTo(address);
        mailSender.send(message);
    }
}

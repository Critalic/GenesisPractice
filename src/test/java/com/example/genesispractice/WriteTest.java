package com.example.genesispractice;

import com.example.genesispractice.service.subscription.SubscriptionService;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class WriteTest {
    private static final String FILENAME="/home/vitalik/Documents/Emails.txt";


    void addSubscriber (String email) {
        try(PrintWriter bufferedWriter = new PrintWriter(new FileWriter(FILENAME, true))) {
            bufferedWriter.println(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addSub() {
        addSubscriber("sdijgo2");
    }
}
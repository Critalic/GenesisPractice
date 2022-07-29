package com.example.genesispractice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

@Component
@Disabled
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

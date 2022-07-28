package com.example.genesispractice.service.subscription;

import java.io.IOException;

public interface SubscriptionService {

    boolean addSubscriber(String email) throws IOException;

    void sendMail(String emailText) throws IOException;
}

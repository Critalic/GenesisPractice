package com.example.genesispractice.controller;

import com.example.genesispractice.service.rate.RateService;
import com.example.genesispractice.service.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class RestControllerRate {

    private final RateService rateService;
    private final SubscriptionService subscriptionService;

    public RestControllerRate(@Qualifier("CoinMarketCap") RateService service, SubscriptionService subscriptionService) {
        this.rateService = service;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHryvniaRate() {
        return rateService.requestHryvniaRate().map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error"));
    }

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSubscriber(@RequestParam String email) {
        subscriptionService.addSubscriber(email);
        return null;
    }

    @PostMapping(value = "/sendEmails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendEmail() {
        subscriptionService.sendMail(rateService.requestHryvniaRate().get());
        return null;
    }
}

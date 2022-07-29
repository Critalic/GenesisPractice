package com.example.genesispractice.controller;

import com.example.genesispractice.service.rate.RateService;
import com.example.genesispractice.service.subscription.SubscriptionService;
import java.io.IOException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class RateRestController {

    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    private final RateService rateService;
    private final SubscriptionService subscriptionService;

    public RateRestController(@Qualifier("CoinMarketCap") RateService service,
                              @Qualifier("FileService") SubscriptionService subscriptionService) {
        this.rateService = service;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHryvniaRate() {
        return ResponseEntity.ok(rateService.requestCurrencyRate("BTC", "UAH", 1));
    }

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSubscriber(@RequestParam @Pattern(regexp = EMAIL_PATTERN,
        message = "Please enter a valid e-mail") String email) throws IOException {

        return subscriptionService.addSubscriber(email) ?
            ResponseEntity.status(HttpStatus.OK).build() :
            ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
    }

    @PostMapping(value = "/sendEmails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendEmail() throws IOException {
        subscriptionService.sendMail(rateService.requestCurrencyRate("BTC", "UAH", 1));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

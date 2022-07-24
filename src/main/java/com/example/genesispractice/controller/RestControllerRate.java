package com.example.genesispractice.controller;

import com.example.genesispractice.model.Rate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/rate")
@RestController
public class RestControllerRate {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> getAllRates() {
        return null;
//        return ResponseEntity.status(HttpStatus.OK).body(mainService.getAllRates());
    }
}

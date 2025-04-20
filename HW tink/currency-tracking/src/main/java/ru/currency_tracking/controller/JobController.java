package ru.currency_tracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.currency_tracking.service.CurrencyRateCheckService;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final CurrencyRateCheckService rateCheckService;

    @PostMapping("/check-rates")
    public ResponseEntity<String> triggerRateCheck() {
        rateCheckService.checkCurrencyRates();
        return ResponseEntity.ok("Currency check job triggered manually");
    }
}
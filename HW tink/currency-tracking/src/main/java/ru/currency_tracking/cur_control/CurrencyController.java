package ru.currency_tracking.cur_control;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.currency_tracking.model.Currency;
import ru.currency_tracking.service.CurrencyService;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PostMapping("/currencies")
    public ResponseEntity<?> createCurrency(@RequestBody Currency currency) {
        try {
            Currency createdCurrency = currencyService.createCurrency(currency);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<?> getCurrencyById(@PathVariable String id) {
        return currencyService.getCurrencyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable String id, @RequestBody Currency currency) {
        try {
            Currency updatedCurrency = currencyService.updateCurrency(id, currency);
            return ResponseEntity.ok(updatedCurrency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable String id) {
        try {
            currencyService.deleteCurrency(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
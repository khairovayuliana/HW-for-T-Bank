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


    private final CurrencyService cur_service;



    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies(){
        return ResponseEntity.ok(cur_service.getCurrencies());
    }

    @PostMapping("/currencies")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency cur) {
        if (cur.getName() == null || cur.getBaseCurrency() == null || cur.getPriceChangeRange() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cur);
        }
        cur_service.addCurrency(cur);
        return ResponseEntity.status(HttpStatus.CREATED).body(cur);
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id){
        return ResponseEntity.ok(cur_service.getCurrencyById(id));
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id, @RequestBody Currency cur){
        if(cur_service.getCurrencyById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cur);
        }
        cur_service.updateCurrency(id, cur);
        return ResponseEntity.status(HttpStatus.OK).body(cur_service.getCurrencyById(id));
    }


    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable String id){
        cur_service.deleteCurrencyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

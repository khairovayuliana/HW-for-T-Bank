package ru.currency_tracking.service;

import org.springframework.stereotype.Service;
import ru.currency_tracking.model.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final List<Currency> currencies = new ArrayList<>();

    public List<Currency> getAllCurrencies() {
        return new ArrayList<>(currencies);
    }

    public Currency createCurrency(Currency currency) {
        if (currency.getId() == null || currency.getId().isEmpty()) {
            throw new IllegalArgumentException("Currency ID cannot be null or empty");
        }


        if (currencies.stream().anyMatch(c -> c.getId().equals(currency.getId()))) {
            throw new IllegalArgumentException("Currency with this ID already exists");
        }

        currencies.add(currency);
        return currency;
    }

    public Optional<Currency> getCurrencyById(String id) {
        return currencies.stream()
                .filter(currency -> currency.getId().equals(id))
                .findFirst();
    }

    public Currency updateCurrency(String id, Currency currencyDetails) {
        Currency existingCurrency = getCurrencyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found"));

        existingCurrency.setName(currencyDetails.getName());
        existingCurrency.setBaseCurrency(currencyDetails.getBaseCurrency());
        existingCurrency.setPriceChangeRange(currencyDetails.getPriceChangeRange());
        existingCurrency.setDescription(currencyDetails.getDescription());

        return existingCurrency;
    }

    public void deleteCurrency(String id) {
        Currency currencyToRemove = getCurrencyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found"));
        currencies.remove(currencyToRemove);
    }
}
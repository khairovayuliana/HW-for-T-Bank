package ru.currency_tracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.currency_tracking.model.Currency;
import ru.currency_tracking.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency createCurrency(Currency currency) {
        if (currency.getId() == null || currency.getId().isEmpty()) {
            throw new IllegalArgumentException("Currency ID cannot be empty");
        }
        return currencyRepository.save(currency);
    }

    public Optional<Currency> getCurrencyById(String id) {
        return currencyRepository.findById(id);
    }

    public Currency updateCurrency(String id, Currency currencyDetails) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found"));

        currency.setName(currencyDetails.getName());
        currency.setBaseCurrency(currencyDetails.getBaseCurrency());
        currency.setPriceChangeRange(currencyDetails.getPriceChangeRange());
        currency.setDescription(currencyDetails.getDescription());

        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String id) {
        currencyRepository.deleteById(id);
    }
}
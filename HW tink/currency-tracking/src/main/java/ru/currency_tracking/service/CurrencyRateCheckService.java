package ru.currency_tracking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.currency_tracking.dto.CbrDailyResponse;
import ru.currency_tracking.dto.CurrencyRate;
import ru.currency_tracking.model.Currency;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateCheckService {
    private static final String CBR_API_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private final CurrencyService currencyService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 0 * * * *") // Runs every hour at minute 0
    public void checkCurrencyRates() {
        log.info("Starting currency rates check...");

        try {
            CbrDailyResponse response = restTemplate.getForObject(CBR_API_URL, CbrDailyResponse.class);
            if (response == null || response.getValute() == null) {
                log.warn("No currency data received from API");
                return;
            }

            List<Currency> trackedCurrencies = currencyService.getAllCurrencies();
            if (trackedCurrencies.isEmpty()) {
                log.info("No currencies are being tracked in the database");
                return;
            }

            for (Currency trackedCurrency : trackedCurrencies) {
                String baseCurrencyCode = trackedCurrency.getBaseCurrency();
                if (baseCurrencyCode == null || baseCurrencyCode.isEmpty()) {
                    continue;
                }

                CurrencyRate rate = response.getValute().get(baseCurrencyCode);
                if (rate == null) {
                    log.warn("Currency {} not found in API response", baseCurrencyCode);
                    continue;
                }

                checkCurrencyChange(trackedCurrency, rate);
            }
        } catch (Exception e) {
            log.error("Error while checking currency rates: {}", e.getMessage(), e);
        }
    }

    private void checkCurrencyChange(Currency trackedCurrency, CurrencyRate rate) {
        double changePercentage = calculatePercentageChange(rate.getPrevious(), rate.getValue());
        String priceChangeRange = trackedCurrency.getPriceChangeRange();

        if (priceChangeRange == null || priceChangeRange.isEmpty()) {
            return;
        }

        try {
            double threshold = parsePercentage(priceChangeRange);
            if (Math.abs(changePercentage) >= Math.abs(threshold)) {
                String direction = changePercentage > 0 ? "вырос" : "упал";
                String message = String.format("%s %s на %.2f%% (было %.4f, стало %.4f)",
                        trackedCurrency.getName(),
                        direction,
                        Math.abs(changePercentage),
                        rate.getPrevious(),
                        rate.getValue());

                if (trackedCurrency.getDescription() != null && !trackedCurrency.getDescription().isEmpty()) {
                    message += ". " + trackedCurrency.getDescription();
                }

                log.info(message);
            }
        } catch (NumberFormatException e) {
            log.warn("Invalid priceChangeRange format for currency {}: {}",
                    trackedCurrency.getName(), priceChangeRange);
        }
    }

    private double calculatePercentageChange(double previous, double current) {
        return ((current - previous) / previous) * 100;
    }

    private double parsePercentage(String percentageStr) throws NumberFormatException {
        String cleaned = percentageStr.replace("%", "").trim();
        return Double.parseDouble(cleaned);
    }
}
package ru.currency_tracking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CbrDailyResponse {
    @JsonProperty("Valute")
    private Map<String, CurrencyRate> valute;
}


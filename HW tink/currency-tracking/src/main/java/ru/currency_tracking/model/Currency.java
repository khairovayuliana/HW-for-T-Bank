package ru.currency_tracking.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Currency {
    private String id;
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;
}
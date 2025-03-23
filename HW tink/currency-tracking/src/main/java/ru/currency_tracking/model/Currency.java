package ru.currency_tracking.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Currency {
    private String name;
    private String id;

    private String baseCurrency;
    private String priceChangeRange;
    private String description;

}

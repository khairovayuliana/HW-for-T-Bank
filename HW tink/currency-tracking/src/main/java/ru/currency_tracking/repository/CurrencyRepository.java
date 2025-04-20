package ru.currency_tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.currency_tracking.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
package ru.currency_tracking.service;


import org.springframework.stereotype.Service;
import ru.currency_tracking.model.Currency;

import java.util.ArrayList;
import java.util.List;


@Service
public class CurrencyService {

    private final List<Currency> currencies = new ArrayList<>();

    public List<Currency> getCurrencies(){
        return currencies;
    }

    public static Currency saveCurrency(Currency currency){
        return currency;
    }

    public List<Currency> addCurrency(Currency cur){
        for(Currency currency1 : currencies){
            if(currency1.getId().equals(cur.getId())){
                return currencies;
            }
        }
        currencies.add(cur);
        return currencies;
    }

    public Currency getCurrencyById(String id){
        return currencies.stream().filter(currency -> currency.getId().equals(id)).findFirst().get();
    }

    public Currency updateCurrency(String id, Currency cur){
        Currency new_cur = new CurrencyService().getCurrencyById(id);
        new_cur.setName(cur.getName());
        new_cur.setBaseCurrency(cur.getBaseCurrency());
        new_cur.setPriceChangeRange(cur.getPriceChangeRange());
        new_cur.setDescription(cur.getDescription());
        return new_cur;
    }

    public void deleteCurrencyById(String id){
            currencies.remove(getCurrencyById(id));
    }


}

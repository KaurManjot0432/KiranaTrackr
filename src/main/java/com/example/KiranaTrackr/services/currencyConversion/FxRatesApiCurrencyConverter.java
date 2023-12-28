package com.example.KiranaTrackr.services.currencyConversion;

import com.example.KiranaTrackr.models.enums.CurrencyType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.KiranaTrackr.utils.currencyConversion.ExchangeRateApiResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Service
public class FxRatesApiCurrencyConverter implements CurrencyConversionService {

    private static final String FX_RATES_API_URL = "https://api.fxratesapi.com/latest";
    private final RestTemplate restTemplate;

    public FxRatesApiCurrencyConverter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts the given amount from one currency to another using the FX Rates API.
     *
     * @param amount       The amount to be converted.
     * @param fromCurrency The source currency.
     * @param toCurrency   The target currency.
     * @return The converted amount in the target currency.
     */
    @Override
    public BigDecimal convert(BigDecimal amount, CurrencyType fromCurrency, CurrencyType toCurrency) {
        // Fetch the latest exchange rates
        Map<String, Object> params = new HashMap<>();
        params.put("base", fromCurrency);
        String url = FX_RATES_API_URL + "?base={base}";

        ExchangeRateApiResponse response = restTemplate.getForObject(url, ExchangeRateApiResponse.class, params);

        // Extract the exchange rate for the target currency
        BigDecimal exchangeRate = response.getRates().get(toCurrency.name());

        // Perform the currency conversion
        return amount.multiply(exchangeRate);
    }
}
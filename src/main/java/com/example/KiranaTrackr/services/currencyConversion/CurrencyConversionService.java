package com.example.KiranaTrackr.services.currencyConversion;

import com.example.KiranaTrackr.models.enums.CurrencyType;

import java.math.BigDecimal;

public interface CurrencyConversionService {
    BigDecimal convert(BigDecimal amount, CurrencyType sourceCurrency, CurrencyType destinationCurrency);
}

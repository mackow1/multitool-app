package pl.kowalczyk.maciej.spring.multitoolapp.service;

import org.junit.jupiter.api.Test;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RapidApiCurrencyExchangeTest {

    @Test
    void currencyExchange() throws IOException {
        // given
        RapidApiCurrencyExchange rapidApiCurrencyExchange = new RapidApiCurrencyExchange();
        RapidApiCurrencyExchangeRequest currencyExchangeRequest = new RapidApiCurrencyExchangeRequest("PLN", "USD");

        // when
        RapidApiCurrencyExchangeResponse currencyExchangeResponse = rapidApiCurrencyExchange.currencyExchange(currencyExchangeRequest);

        // then

    }
}
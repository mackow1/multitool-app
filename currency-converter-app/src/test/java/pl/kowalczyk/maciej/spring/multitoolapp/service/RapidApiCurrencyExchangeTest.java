package pl.kowalczyk.maciej.spring.multitoolapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kowalczyk.maciej.spring.multitoolapp.api.exception.RapidApiCurrencyExchangeException;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RapidApiCurrencyExchangeTest {

    @Test
    void currencyExchangeWithRequestNull() throws IOException, RapidApiCurrencyExchangeException {
        // given
        RapidApiCurrencyExchange rapidApiCurrencyExchange = new RapidApiCurrencyExchange();

        // when
        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional =
                rapidApiCurrencyExchange.currencyExchange(null);

        RapidApiCurrencyExchangeResponse currencyExchangeResponse = currencyExchangeResponseOptional.orElseThrow(
                () -> new RapidApiCurrencyExchangeException("Unable to exchange currency")
        );

        // then
        Assertions.assertNotNull(currencyExchangeResponse, "Response is NULL");
    }

    @Test
    void currencyExchangeWithRequest() throws IOException, RapidApiCurrencyExchangeException {
        // given
        RapidApiCurrencyExchange rapidApiCurrencyExchange = new RapidApiCurrencyExchange();
        RapidApiCurrencyExchangeRequest currencyExchangeRequest = new RapidApiCurrencyExchangeRequest("PLN", "USD");

        // when
        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional =
                rapidApiCurrencyExchange.currencyExchange(currencyExchangeRequest);

        RapidApiCurrencyExchangeResponse currencyExchangeResponse = currencyExchangeResponseOptional.orElseThrow(
                () -> new RapidApiCurrencyExchangeException("Unable to exchange currency")
        );

        // then
        Assertions.assertNotNull(currencyExchangeResponse, "Response is NULL");
    }
}
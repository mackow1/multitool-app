package pl.kowalczyk.maciej.spring.multitoolapp.service;

import okhttp3.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kowalczyk.maciej.spring.multitoolapp.api.exception.RapidApiCurrencyExchangeException;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class RapidApiCurrencyExchangeServiceTest {

    @Autowired
    private RapidApiCurrencyExchangeService rapidApiCurrencyExchangeService;

    private static final String URL = "https://currency-exchange.p.rapidapi.com/listquotes";
    private static final String URL_EXCEPTION = "https://currency-exchange.p.rapidapi.com/listquotesbad";

    @Test
    void currencyExchangeWithRequestNull() throws IOException, RapidApiCurrencyExchangeException {
        // given

        // when
        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional =
                rapidApiCurrencyExchangeService.currencyExchange(null);

        // then
//        Assertions.assertThrows(RapidApiCurrencyExchangeException.class, () -> {
//                    rapidApiCurrencyExchangeService.currencyExchange(null);
//                },
//                "Expected RapidApiCurrencyExchangeException, but it was not thrown"
//        );
        Assertions.assertEquals(Optional.empty(), currencyExchangeResponseOptional, "Expected Optional.empty()");
    }

    @Test
    void currencyExchangeWithRequest() throws IOException, RapidApiCurrencyExchangeException {
        // given
        RapidApiCurrencyExchangeRequest currencyExchangeRequest = new RapidApiCurrencyExchangeRequest("PLN", "USD");

        // when
        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional =
                rapidApiCurrencyExchangeService.currencyExchange(currencyExchangeRequest);

        RapidApiCurrencyExchangeResponse currencyExchangeResponse = currencyExchangeResponseOptional.orElseThrow(
                () -> new RapidApiCurrencyExchangeException("Unable to exchange currency")
        );

        // then
        Assertions.assertNotNull(currencyExchangeResponse, "Response is NULL");
    }

    @Test
    void list() throws RapidApiCurrencyExchangeException {
        // given

        // when
        List<String> currencies = rapidApiCurrencyExchangeService.list();

        // then
        Assertions.assertNotNull(currencies, "List is NULL");
    }

    @Test
    void buildRequest() {
        // given

        // when
        Request request = rapidApiCurrencyExchangeService.buildRequest(URL);

        // then
        Assertions.assertNotNull(request, "Request is NULL");
    }
}

package pl.kowalczyk.maciej.spring.multitoolapp.service;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.kowalczyk.maciej.spring.multitoolapp.api.exception.RapidApiCurrencyExchangeException;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;
import pl.kowalczyk.maciej.spring.multitoolapp.service.utils.UrlBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RapidApiCurrencyExchangeService {

    private static final Logger LOGGER = Logger.getLogger(RapidApiCurrencyExchangeService.class.getName());

    public Optional<RapidApiCurrencyExchangeResponse> currencyExchange(
            RapidApiCurrencyExchangeRequest currencyExchangeRequest) throws RapidApiCurrencyExchangeException {
        LOGGER.info("currencyExchange(" + currencyExchangeRequest + ")");

        if (currencyExchangeRequest != null) {
            OkHttpClient client = new OkHttpClient();
            HttpUrl url = UrlBuilder.build(currencyExchangeRequest.getCurrencyFrom(), currencyExchangeRequest.getCurrencyTo(), "1.0");

            // TODO: oddzielna metoda
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("X-RapidAPI-Key", "e0d6da3682msh10c9940bca9e892p1a25e2jsn005826af4a87")
                    .addHeader("X-RapidAPI-Host", "currency-exchange.p.rapidapi.com")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response != null) {
                    if (response.body() != null) {
                        // TODO: oddzielna metoda
                        String result = response.body().string();
                        BigDecimal convertedResult = BigDecimal.valueOf(Double.parseDouble(result));
                        RapidApiCurrencyExchangeResponse currencyExchangeResponse = new RapidApiCurrencyExchangeResponse(convertedResult);
                        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional = Optional.ofNullable(currencyExchangeResponse);

                        LOGGER.info("currencyExchange(...) = " + currencyExchangeResponseOptional);
                        return currencyExchangeResponseOptional;
                    }
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Unable to execute request", e);
                throw new RapidApiCurrencyExchangeException("Unable to execute request", e);
            }
        }

        LOGGER.info("currencyExchange(...) = " + Optional.empty());
        return Optional.empty();
    }
}

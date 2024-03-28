package pl.kowalczyk.maciej.spring.multitoolapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kowalczyk.maciej.spring.multitoolapp.api.exception.RapidApiCurrencyExchangeException;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;
import pl.kowalczyk.maciej.spring.multitoolapp.service.utils.UrlBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RapidApiCurrencyExchangeService {

    private static final Logger LOGGER = Logger.getLogger(RapidApiCurrencyExchangeService.class.getName());

    @Value("${rapidapi-key}")
    private String apiKey;

    @Value("${rapidapi-host}")
    private String apiHost;

    public List<String> list() throws RapidApiCurrencyExchangeException {
        LOGGER.info("list()");

        OkHttpClient client = new OkHttpClient();

//        Request request = new Request.Builder()
//                .url("https://currency-exchange.p.rapidapi.com/listquotes")
//                .get()
//                .addHeader("X-RapidAPI-Key", apiKey)
//                .addHeader("X-RapidAPI-Host", apiHost)
//                .build();

        Request request = buildRequest("https://currency-exchange.p.rapidapi.com/listquotes");

        try (Response response = client.newCall(request).execute()) {
            if (response != null) {
                if (response.body() != null) {

                    String currenciesListResponseBodyString = response.body().string();

                    ObjectMapper objectMapper = new ObjectMapper();
                    List<String> currenciesListFromJson = objectMapper.readValue(
                            currenciesListResponseBodyString, (new TypeReference<List<String>>() {
                            }));

                    LOGGER.info("list(...) = " + currenciesListFromJson);
                    return currenciesListFromJson;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to get list of currencies", e);
            throw new RapidApiCurrencyExchangeException("Unable to get list of currencies", e);
        }

        LOGGER.info("list(...) = " + Optional.empty());
        return new ArrayList<>();
    }


    public Optional<RapidApiCurrencyExchangeResponse> currencyExchange(
            RapidApiCurrencyExchangeRequest currencyExchangeRequest) throws RapidApiCurrencyExchangeException {
        LOGGER.info("currencyExchange(" + currencyExchangeRequest + ")");

        if (currencyExchangeRequest != null) {
            OkHttpClient client = new OkHttpClient();
            String url = UrlBuilder.build(currencyExchangeRequest.getCurrencyFrom(), currencyExchangeRequest.getCurrencyTo(), "1.0");

            // TODO: oddzielna metoda
//            Request request = new Request.Builder()
//                    .url(url)
//                    .get()
//                    .addHeader("X-RapidAPI-Key", apiKey)
//                    .addHeader("X-RapidAPI-Host", apiHost)
//                    .build();

            Request request = buildRequest(url);

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

    Request buildRequest(String url) {
        LOGGER.info("buildRequest(" + url + ")");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();

        LOGGER.info("buildRequest(...) = " + request);
        return request;
    }
}

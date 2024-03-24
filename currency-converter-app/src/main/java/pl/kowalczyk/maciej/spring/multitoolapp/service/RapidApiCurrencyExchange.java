package pl.kowalczyk.maciej.spring.multitoolapp.service;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;
import pl.kowalczyk.maciej.spring.multitoolapp.service.utils.UrlBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class RapidApiCurrencyExchange {

    private static final Logger LOGGER = Logger.getLogger(RapidApiCurrencyExchange.class.getName());

    public RapidApiCurrencyExchangeResponse currencyExchange(RapidApiCurrencyExchangeRequest currencyExchangeRequest) throws IOException {
        LOGGER.info("currencyExchange(" + currencyExchangeRequest + ")");

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = UrlBuilder.build(currencyExchangeRequest.getCurrencyFrom(), currencyExchangeRequest.getCurrencyTo(), "1.0");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "e0d6da3682msh10c9940bca9e892p1a25e2jsn005826af4a87")
                .addHeader("X-RapidAPI-Host", "currency-exchange.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        String result = null;

        if (response != null) {
            if (response.body() != null) {
                result = response.body().string();
            }
        }

        BigDecimal convertedResult = BigDecimal.valueOf(Double.parseDouble(result));
//        BigDecimal convertedResult = new BigDecimal(result);

        LOGGER.info("currencyExchange(...) = " + convertedResult);
        return null;
    }
}

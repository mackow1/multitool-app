package pl.kowalczyk.maciej.spring.multitoolapp.service.utils;

import okhttp3.HttpUrl;

import java.util.logging.Logger;

public class UrlBuilder {

    private static final Logger LOGGER = Logger.getLogger(UrlBuilder.class.getName());

    public static HttpUrl build(String currencyFrom, String currencyTo, String amount) {
        LOGGER.info("build(" + currencyFrom + ", " + currencyTo + ", " + amount + ")");

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("currency-exchange.p.rapidapi.com")
                .addPathSegment("exchange")
                .addQueryParameter("from", currencyFrom)
                .addQueryParameter("to", currencyTo)
                .addQueryParameter("q", amount)
                .build();

        LOGGER.info("build(...) = " + httpUrl);
        return httpUrl;
    }
}

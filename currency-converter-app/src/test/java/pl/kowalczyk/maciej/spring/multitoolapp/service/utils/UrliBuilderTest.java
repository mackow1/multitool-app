package pl.kowalczyk.maciej.spring.multitoolapp.service.utils;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UrliBuilderTest {

    public static final String URL = "https://currency-exchange.p.rapidapi.com/exchange?from=PLN&to=USD&q=1.0";

    @Test
    void buildUrl() {
        // given

        // when
        String url = UrlBuilder.build("PLN", "USD", "1.0");

        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(url, "Url is NULL"),
                () -> Assertions.assertEquals(URL, url, "Urls are not the same")
        );
    }
}
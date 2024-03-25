package pl.kowalczyk.maciej.spring.multitoolapp.api.exception;

public class RapidApiCurrencyExchangeException extends Exception {

    public RapidApiCurrencyExchangeException(String message) {
        super(message);
    }

    public RapidApiCurrencyExchangeException(String message, Throwable cause) {
        super(message, cause);
    }
}

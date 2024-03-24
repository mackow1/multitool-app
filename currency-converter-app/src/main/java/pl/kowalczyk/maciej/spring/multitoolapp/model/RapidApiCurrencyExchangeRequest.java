package pl.kowalczyk.maciej.spring.multitoolapp.model;

public class RapidApiCurrencyExchangeRequest {

    private String currencyFrom;
    private String currencyTo;

    public RapidApiCurrencyExchangeRequest(String currencyFrom, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    @Override
    public String toString() {
        return "RapidApiCurrencyExchangeRequest{" +
                "currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                '}';
    }
}

package pl.kowalczyk.maciej.spring.multitoolapp.model;

import java.math.BigDecimal;

public class RapidApiCurrencyExchangeResponse {

    private BigDecimal result;

    public RapidApiCurrencyExchangeResponse(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RapidApiCurrencyExchangeResponse{" +
                "result=" + result +
                '}';
    }
}

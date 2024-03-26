package pl.kowalczyk.maciej.spring.multitoolapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.kowalczyk.maciej.spring.multitoolapp.api.exception.RapidApiCurrencyExchangeException;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeRequest;
import pl.kowalczyk.maciej.spring.multitoolapp.model.RapidApiCurrencyExchangeResponse;
import pl.kowalczyk.maciej.spring.multitoolapp.service.RapidApiCurrencyExchangeService;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/currencies")
@SessionAttributes(value = {"exchangedCurrency"})
public class RapidApiCurrencyExchangeController {

    private static final Logger LOGGER = Logger.getLogger(RapidApiCurrencyExchangeController.class.getName());

    private RapidApiCurrencyExchangeService rapidApiCurrencyExchangeService;

    public RapidApiCurrencyExchangeController(RapidApiCurrencyExchangeService rapidApiCurrencyExchangeService) {
        this.rapidApiCurrencyExchangeService = rapidApiCurrencyExchangeService;
    }

    @GetMapping
    public String list() {
        LOGGER.info("list()");

        String result = null;

        LOGGER.info("list(...) = " + result);
        return result;
    }

    @GetMapping(value = "/exchange")
    public String exchangeView() {
        LOGGER.info("exchangeView()");

        String result = "currency-exchange";

        LOGGER.info("exchangeView(...) = " + result);
        return result;
    }

    @PostMapping(value = "/exchange")
    public String exchange(RapidApiCurrencyExchangeRequest rapidApiCurrencyExchangeRequest, ModelMap modelMap) throws RapidApiCurrencyExchangeException {
        LOGGER.info("exchange(" + rapidApiCurrencyExchangeRequest + ")");

        Optional<RapidApiCurrencyExchangeResponse> currencyExchangeResponseOptional = rapidApiCurrencyExchangeService.currencyExchange(rapidApiCurrencyExchangeRequest);
        RapidApiCurrencyExchangeResponse currencyExchangeResponse = currencyExchangeResponseOptional.orElseThrow(
                () -> new RapidApiCurrencyExchangeException("Unable to exchange currency")
        );

        modelMap.addAttribute("exchangedCurrency", currencyExchangeResponse);

        String result = "redirect:/currencies/exchange";

        LOGGER.info("exchange(...) = " + result);
        return result;
    }
}

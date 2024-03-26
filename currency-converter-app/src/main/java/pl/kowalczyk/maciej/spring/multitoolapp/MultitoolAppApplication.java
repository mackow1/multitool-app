package pl.kowalczyk.maciej.spring.multitoolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultitoolAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultitoolAppApplication.class, args);
	}

}


// TODO: 22.03.2024 jak przesłać request parameters GET - zaimplementować

// TODO: 25.03.2024 Dla RapidApiCurrencyExchange wartości w stringach przenieść do application.properties i użyć mechanizmu spring, wydzielić mniejsze metody w ramach refaktoryzcji (service)
// https://www.baeldung.com/properties-with-spring
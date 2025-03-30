package edu.dp.sau.danmishchenko.service;

import edu.dp.sau.danmishchenko.model.CurrencyRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class PrivatBankService {

    private static final String PRIVATBANK_API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public CurrencyRate getCurrencyRate() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            CurrencyRate[] currencyRates = restTemplate.getForObject(PRIVATBANK_API_URL, CurrencyRate[].class);
            if (currencyRates != null) {
                for (CurrencyRate rate : currencyRates) {
                    if (rate.getCcy().equals("USD")) {
                        return rate;
                    }
                }
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
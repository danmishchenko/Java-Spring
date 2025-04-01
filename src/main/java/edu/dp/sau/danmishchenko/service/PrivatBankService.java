package edu.dp.sau.danmishchenko.service;

import edu.dp.sau.danmishchenko.model.CurrencyRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PrivatBankService {

    private static final String PRIVATBANK_API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final Logger logger = LoggerFactory.getLogger(PrivatBankService.class);

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
            logger.error("Помилка отримання курсу валют з ПриватБанку: " + e.getMessage());
        }
        return null;
    }
}
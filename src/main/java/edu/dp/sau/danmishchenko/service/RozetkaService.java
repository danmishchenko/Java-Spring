package edu.dp.sau.danmishchenko.service;

import edu.dp.sau.danmishchenko.model.Smartphone;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RozetkaService {

    private static final String ROZETKA_URL = "https://rozetka.com.ua/ua/";

    public List<Smartphone> searchSmartphones(String query) {
        try {
            Document document = Jsoup.connect(ROZETKA_URL + "search/?text=" + query).get();
            Elements items = document.select(".goods-tile");
            List<Smartphone> smartphones = new ArrayList<>();
            for (Element item : items) {
                String name = item.select(".goods-tile__title").text();
                String price = item.select(".goods-tile__price-value").text();
                String link = item.select(".goods-tile__heading a").attr("href");
                if (!name.isEmpty() && !price.isEmpty() && !link.isEmpty()) {
                    smartphones.add(new Smartphone(name, price, link));
                }
            }
            return smartphones;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Smartphone> getAllSmartphones() {
        try {
            Document document = Jsoup.connect(ROZETKA_URL + "mobile-phones/c80003/").get();
            Elements items = document.select(".goods-tile");
            List<Smartphone> smartphones = new ArrayList<>();
            for (Element item : items) {
                String name = item.select(".goods-tile__title").text();
                String price = item.select(".goods-tile__price-value").text();
                String link = item.select(".goods-tile__heading a").attr("href");
                if (!name.isEmpty() && !price.isEmpty() && !link.isEmpty()) {
                    smartphones.add(new Smartphone(name, price, link));
                }
            }
            return smartphones;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
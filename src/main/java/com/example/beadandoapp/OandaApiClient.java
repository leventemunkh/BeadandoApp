package com.example.beadandoapp;

import org.json.JSONObject;
import org.json.JSONArray;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class OandaApiClient {
    private static final String API_KEY = "54152a8b9e7a06f52e9270fffd5270af-8cc3a49c7238aeb8e77ed42e6f09a917";
    private static final String BASE_URL = "https://api-fxpractice.oanda.com/v3/";

    private final HttpClient httpClient;

    public OandaApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Általános GET kérés küldése
    public String sendGetRequest(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Authorization", "Bearer " + API_KEY)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.statusCode() + " - " + response.body());
        }
    }

    // Elérhető devizapárok lekérdezése
    public List<String> getAvailableInstruments() throws Exception {
        String response = sendGetRequest("accounts/101-004-30448182-001/instruments");
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray instruments = jsonResponse.getJSONArray("instruments");

        List<String> currencyPairs = new ArrayList<>();
        for (int i = 0; i < instruments.length(); i++) {
            JSONObject instrument = instruments.getJSONObject(i);
            currencyPairs.add(instrument.getString("name"));
        }
        return currencyPairs;
    }

    // Aktuális ár lekérdezése egy devizapárra
    public String getCurrentPrice(String instrument) throws Exception {
        String response = sendGetRequest("pricing?instruments=" + instrument);
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray prices = jsonResponse.getJSONArray("prices");

        if (prices.length() > 0) {
            JSONObject priceInfo = prices.getJSONObject(0);
            String bid = priceInfo.getString("bid");
            String ask = priceInfo.getString("ask");
            return "Bid: " + bid + ", Ask: " + ask;
        } else {
            throw new RuntimeException("No pricing information available for instrument: " + instrument);
        }
    }

    public List<PriceData> getHistoricalPrices(String currencyPair, LocalDate startDate, LocalDate endDate) throws Exception {
        String endpoint = "instruments/" + currencyPair + "/candles?granularity=D&from=" + startDate + "&to=" + endDate;
        String response = sendGetRequest(endpoint);

        // JSON feldolgozása
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray candles = jsonResponse.getJSONArray("candles");

        List<PriceData> prices = new ArrayList<>();
        for (int i = 0; i < candles.length(); i++) {
            JSONObject candle = candles.getJSONObject(i);
            String date = candle.getString("time").split("T")[0];
            double price = candle.getJSONObject("mid").getDouble("c");
            prices.add(new PriceData(date, price));
        }

        return prices;
    }

}

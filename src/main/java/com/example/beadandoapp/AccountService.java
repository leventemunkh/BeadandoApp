package com.example.beadandoapp;

import org.json.JSONObject;

public class AccountService {
    private final OandaApiClient apiClient;

    public AccountService() {
        this.apiClient = new OandaApiClient();
    }

    public String getAccountDetails() {
        try {
            String endpoint = "accounts";
            String response = apiClient.sendGetRequest(endpoint);

            // Válasz feldolgozása JSON-ként
            JSONObject json = new JSONObject(response);
            return json.toString(4); // Formázott szöveg a megjelenítéshez
        } catch (Exception e) {
            e.printStackTrace();
            return "Hiba történt az adatok lekérdezésekor.";
        }
    }
}

package com.example.beadandoapp;

import javax.xml.soap.*;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;


public class SOAPClient {

    private static final String SOAP_ENDPOINT = "http://www.mnb.hu/arfolyamok.asmx";
    private static final String NAMESPACE_URI = "http://www.mnb.hu/webservices/";
    private static final String NAMESPACE_PREFIX = "mnb";

    /**
     * Minden adat letöltése és mentése a fájlba.
     *
     * @param fileName A fájl neve, ahová az adatokat mentjük.
     * @throws Exception Hibakezeléshez.
     */
    public static void downloadAllData(String fileName) throws Exception {
        SOAPMessage request = createSOAPRequest("GetCurrentExchangeRates", null);
        SOAPMessage response = sendSOAPRequest(request);

        // Adatok mentése a fájlba
        saveSOAPResponseToFile(response, fileName);
        System.out.println("Minden adat sikeresen letöltve és elmentve.");
    }

    /**
     * Szűrt adatok letöltése és mentése a fájlba.
     *
     * @param fileName    A fájl neve, ahová az adatokat mentjük.
     * @param date        A dátum szűréshez (pl. "2024-11-29").
     * @param currency    A valuta (pl. "EUR").
     * @param activeOnly  Csak aktív adatok letöltése.
     * @throws Exception Hibakezeléshez.
     */
    public static void downloadFilteredData(String fileName, String date, String currency, boolean activeOnly) throws Exception {
        // SOAP kérés építése szűrők szerint
        SOAPMessage request = createSOAPRequest("GetExchangeRates", date, currency, activeOnly);
        SOAPMessage response = sendSOAPRequest(request);

        // Adatok mentése a fájlba
        saveSOAPResponseToFile(response, fileName);
        System.out.println("Szűrt adatok sikeresen letöltve és elmentve.");
    }

    /**
     * Grafikonhoz adatok letöltése (dummy adatokkal).
     *
     * @param currency A választott valuta (pl. "EUR").
     * @return A letöltött adatok listája.
     */
    public static List<Double> getGraphData(String currency) {
        // Dummy adatok
        List<Double> data = new ArrayList<>();
        data.add(1.1);
        data.add(1.2);
        data.add(1.4);
        data.add(1.6);
        data.add(1.7);
        System.out.println("Grafikonhoz adatok generálva: " + currency);
        return data;
    }

    /**
     * SOAP kérés létrehozása adott művelethez és paraméterekhez.
     *
     * @param operationName A SOAP művelet neve (pl. "GetCurrentExchangeRates").
     * @param params        A művelet paraméterei.
     * @return A SOAPMessage objektum.
     * @throws Exception Hibakezeléshez.
     */
    private static SOAPMessage createSOAPRequest(String operationName, Object... params) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // SOAP envelope és body létrehozása
        SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
        envelope.addNamespaceDeclaration(NAMESPACE_PREFIX, NAMESPACE_URI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElement = soapBody.addChildElement(operationName, NAMESPACE_PREFIX);

        // Paraméterek hozzáadása
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                soapBodyElement.addChildElement("startDate", NAMESPACE_PREFIX).addTextNode((String) params[0]);
            }
            if (params.length > 1 && params[1] != null) {
                soapBodyElement.addChildElement("currency", NAMESPACE_PREFIX).addTextNode((String) params[1]);
            }
            if (params.length > 2) {
                soapBodyElement.addChildElement("activeOnly", NAMESPACE_PREFIX).addTextNode(params[2].toString());
            }
        }

        soapMessage.saveChanges();
        return soapMessage;
    }

    /**
     * SOAP kérés elküldése az MNB szerverhez.
     *
     * @param soapMessage A küldendő SOAP kérés.
     * @return A válasz SOAPMessage objektumként.
     * @throws Exception Hibakezeléshez.
     */
    private static SOAPMessage sendSOAPRequest(SOAPMessage soapMessage) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage response = soapConnection.call(soapMessage, SOAP_ENDPOINT);
        soapConnection.close();
        return response;
    }

    /**
     * SOAP válasz mentése egy fájlba.
     *
     * @param soapMessage A válasz SOAPMessage objektumként.
     * @param fileName    A fájl neve, ahová az adatokat mentjük.
     * @throws Exception Hibakezeléshez.
     */
    private static void saveSOAPResponseToFile(SOAPMessage soapMessage, String fileName) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            soapMessage.writeTo(fos);
            System.out.println("SOAP válasz sikeresen mentve: " + fileName);
        } catch (Exception e) {
            System.err.println("Hiba a SOAP válasz mentése során: " + e.getMessage());
            throw e;
        }
    }

}

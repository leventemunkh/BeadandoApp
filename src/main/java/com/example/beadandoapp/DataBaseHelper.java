package com.example.beadandoapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.function.BiConsumer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;





public class DataBaseHelper {
    private static final String URL = "jdbc:sqlite:src/main/resources/beadando.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Kapcsolódás sikeres az adatbázishoz!");
        } catch (Exception e) {
            System.err.println("Kapcsolódási hiba: " + e.getMessage());
        }
        return conn;
    }

    public static void readData() {
        String query = "SELECT * FROM ut"; // Példa lekérdezés a 'ut' táblára
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Tanösvény: " + rs.getString("nev") +
                        ", Hossz: " + rs.getDouble("hossz") +
                        ", Állomások száma: " + rs.getInt("allomas"));
            }
        } catch (Exception e) {
            System.err.println("Lekérdezési hiba: " + e.getMessage());
        }
    }

    public static void addRecord(String name, double length, int station) {
        String sql = "INSERT INTO ut (nev, hossz, allomas) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, length);
            pstmt.setInt(3, station);

            pstmt.executeUpdate();
            System.out.println("Rekord sikeresen hozzáadva: " + name);
        } catch (Exception e) {
            System.err.println("Hiba a rekord hozzáadásakor: " + e.getMessage());
        }
    }

    public static void getRecords(BiConsumer<Integer, String> consumer) {
        String sql = "SELECT azon, nev FROM ut";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("azon"); // Az azonosító most az "azon" oszlop
                String name = rs.getString("nev");
                consumer.accept(id, name);
            }
        } catch (Exception e) {
            System.err.println("Hiba a rekordok lekérdezésekor: " + e.getMessage());
        }
    }


    public static Map<String, Object> getRecordById(int id) {
        String sql = "SELECT * FROM ut WHERE azon = ?";
        Map<String, Object> record = new HashMap<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                record.put("nev", rs.getString("nev"));
                record.put("hossz", rs.getDouble("hossz"));
                record.put("allomas", rs.getInt("allomas"));
                record.put("ido", rs.getDouble("ido"));
                record.put("vezetes", rs.getInt("vezetes"));
                record.put("telepulesid", rs.getInt("telepulesid"));
            }
        } catch (Exception e) {
            System.err.println("Hiba a rekord lekérdezésekor: " + e.getMessage());
        }

        return record;
    }


    public static void updateRecord(int id, String name, double length, int station) {
        String sql = "UPDATE ut SET nev = ?, hossz = ?, allomas = ? WHERE azon = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, length);
            pstmt.setInt(3, station);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
            System.out.println("Rekord sikeresen módosítva: " + name);
        } catch (Exception e) {
            System.err.println("Hiba a rekord módosításakor: " + e.getMessage());
        }
    }

    public static void deleteRecord(int id) {
        String sql = "DELETE FROM ut WHERE azon = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Rekord sikeresen törölve: ID = " + id);
        } catch (Exception e) {
            System.err.println("Hiba a rekord törlésekor: " + e.getMessage());
        }
    }

    public static List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        String sql = "SELECT azon, nev, hossz, allomas FROM ut";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                records.add(new Record(
                        rs.getInt("azon"),
                        rs.getString("nev"),
                        rs.getDouble("hossz"),
                        rs.getInt("allomas")
                ));
            }
        } catch (Exception e) {
            System.err.println("Hiba a rekordok lekérdezésekor: " + e.getMessage());
        }
        return records;
    }

    public static List<Record> getFilteredRecords(String name, String lengthCriteria, boolean lessStations, boolean guidedOnly) {
        List<Record> records = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT azon, nev, hossz, allomas FROM ut WHERE 1=1");

        // Szűrés név szerint
        if (name != null && !name.isEmpty()) {
            sql.append(" AND nev LIKE ?");
        }

        // Szűrés hossz szerint
        if (lengthCriteria != null) {
            switch (lengthCriteria) {
                case "Kisebb mint 5 km":
                    sql.append(" AND hossz < 5");
                    break;
                case "5-10 km között":
                    sql.append(" AND hossz BETWEEN 5 AND 10");
                    break;
                case "Nagyobb mint 10 km":
                    sql.append(" AND hossz > 10");
                    break;
            }
        }

        // Szűrés állomások száma szerint
        if (lessStations) {
            sql.append(" AND allomas < 5");
        }

        // Csak vezetett túrák
        if (guidedOnly) {
            sql.append(" AND vezetes = 1");
        }

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (name != null && !name.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + name + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                records.add(new Record(
                        rs.getInt("azon"),
                        rs.getString("nev"),
                        rs.getDouble("hossz"),
                        rs.getInt("allomas")
                ));
            }
        } catch (Exception e) {
            System.err.println("Hiba a szűrés során: " + e.getMessage());
        }
        return records;
    }






}

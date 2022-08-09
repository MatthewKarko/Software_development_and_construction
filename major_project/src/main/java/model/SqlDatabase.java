package model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * This class is responsible for creating and maintaining the cached database
 */
public class SqlDatabase {

    private static final String DB_NAME = "StockInfo.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME;

    /**
     * Creating the sql database
     */

    public void createDB() {
        File dbFile = new File(DB_NAME);
        if (dbFile.exists()) {
            return;
        }

        try (Connection ignored = DriverManager.getConnection(DB_URL)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Setting up the required tables for the cache system
     */

    public void setupDB() {
        String createStockInfo =
                """
                CREATE TABLE IF NOT EXISTS StockInfo (
                    symbol text NOT NULL,
                    name text NOT NULL,
                    description text NOT NULL
             
                );
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement()) {
            statement.execute(createStockInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Inserting the inputted information into the cache data
     * @param symbol The ticker symbol for the stock
     * @param name The name of the company
     * @param description The company overview information
     */

    public void insertStockInfo(String symbol, String name, String description) {
        String addSingleStudentWithParametersSQL =
                """
                INSERT INTO StockInfo(symbol, name, description) VALUES
                    (?, ?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Returns the cached stock information
     * @param symbol The stock ticker that is requesting the information
     * @return An arraylist of the stock information
     */

    public ArrayList<String> getStockInfo(String symbol) {
        String enrolmentSQL =
                """
                SELECT *
                FROM StockInfo
                WHERE symbol = ?
                """;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(enrolmentSQL)) {
            preparedStatement.setString(1, symbol);
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<String> info = new ArrayList<>();
            while (results.next()) {
                info.add(results.getString("symbol"));
                info.add(results.getString("name"));
                info.add(results.getString("description"));
            }
            return info;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return new ArrayList<>();
    }

    /**
     * Remove all cached data from the table
     */

    public void removeCachedData() {
        String deleteStockInfo =
                """
                DELETE FROM StockInfo
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement()) {
            statement.execute(deleteStockInfo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }



}








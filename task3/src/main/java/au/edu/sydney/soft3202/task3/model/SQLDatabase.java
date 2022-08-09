package au.edu.sydney.soft3202.task3.model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class SQLDatabase {
    private static final String dbName = "gameSaves.db";
    private static final String dbURL = "jdbc:sqlite:" + dbName;

    public void createDB() {
        File dbFile = new File(dbName);
        if (dbFile.exists()) {
            System.out.println("Database already created");
            return;
        }
        try (Connection ignored = DriverManager.getConnection(dbURL)) {
            // If we get here that means no exception raised from getConnection -
            //meaning it worked
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void setupDB() {
        String createGameSaves =
                """
                CREATE TABLE IF NOT EXISTS gameSaves (
                    user_id integer NOT NULL,
                    saveName text NOT NULL,
                    saveData text NOT NULL
             
                );
                """;
        String createUsers =
                """
                CREATE TABLE IF NOT EXISTS users (
                    user_id integer NOT NULL,
                    name text NOT NULL
                  
                );
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement statement = conn.createStatement()) {
            statement.execute(createGameSaves);
            statement.execute(createUsers);
            System.out.println("Created tables");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void insertGameSave(Integer user_id, String saveName, String saveData) {
        String addSingleStudentWithParametersSQL =
                """
                INSERT INTO gameSaves(user_id, saveName, saveData) VALUES
                    (?, ?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, saveName);
            preparedStatement.setString(3, saveData);
            preparedStatement.executeUpdate();
            System.out.println("Added data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
    public void deleteGameSave(Integer user_id, String saveName) {
        String addSingleStudentWithParametersSQL =
                """
                DELETE FROM gameSaves
                WHERE user_id = ? AND saveName = ?
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, saveName);
            preparedStatement.executeUpdate();
            System.out.println("Added data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
    public void insertUser(Integer user_id, String name) {
        String addSingleStudentWithParametersSQL =
                """
                INSERT INTO users(user_id, name) VALUES
                    (?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            System.out.println("Added data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public Boolean getUsers(String name) {
        String studentRangeSQL =
                """
                SELECT *
                FROM users
                WHERE name = ?
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(studentRangeSQL)) {
            preparedStatement.setString(1, name);
            ResultSet results = preparedStatement.executeQuery();
            if (!results.next()) {
                return false;
            }
            System.out.println("Finished simple query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return true;
    }
    public Integer getUserID(String name) {
        String studentRangeSQL =
                """
                SELECT user_id
                FROM users
                WHERE name = ?
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(studentRangeSQL)) {
            preparedStatement.setString(1, name);
            ResultSet results = preparedStatement.executeQuery();
            System.out.println(results.getInt("user_id"));
            return results.getInt("user_id");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return -1;
    }

    public ArrayList<String> getListOfUserSaves(String username) {
        String enrolmentSQL =
                """
                SELECT saveName
                FROM gameSaves
                INNER JOIN users ON gameSaves.user_id = users.user_id
                WHERE users.name = ?
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(enrolmentSQL)) {
            preparedStatement.setString(1, username);
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<String> saves = new ArrayList<>();
            while (results.next()) {
                saves.add(results.getString("saveName"));
            }
            System.out.println("Finished join query");
            System.out.println(saves);
            return saves;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return new ArrayList<>();
    }
    public String getSaveData(String saveName, Integer user_id) {
        String enrolmentSQL =
                """
                SELECT saveData
                FROM gameSaves
                INNER JOIN users ON gameSaves.user_id = users.user_id
                WHERE gameSaves.saveName = ? AND gameSaves.user_id = ?
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement =
                     conn.prepareStatement(enrolmentSQL)) {
            preparedStatement.setString(1, saveName);
            preparedStatement.setInt(2, user_id);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                return results.getString("saveData");
            }
            System.out.println("Finished join query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return "";
    }
}

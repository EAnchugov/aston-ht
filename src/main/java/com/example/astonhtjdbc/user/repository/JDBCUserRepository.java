package com.example.astonhtjdbc.user.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class JDBCUserRepository {

    private static final String URL = "jdbc:postgresql://localhost:6541/aston-ht-jdbc";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "iamroot";

    public void addUser(String userName, String userEmail) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userEmail);
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Успешно добавлена новая запись!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка взаимодействия с базой данных: " + e.getMessage());
        }
    }

    public void updateUser(Long id, String userName, String userEmail) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Задайте значения для параметров запроса
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userEmail);
                preparedStatement.setLong(3, id);
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Запись №" + id + " успешно обновлена!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка взаимодействия с базой данных: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "DELETE from users where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Запись №" + id + " успешно удалена!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка взаимодействия с базой данных: " + e.getMessage());
        }
    }

    public String getById(Long id) {
        String result = "";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "select * from users where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String pk = rs.getString(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    result = "id:" + pk + ", " + "name" + name + ", " + "email" + email;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка взаимодействия с базой данных: " + e.getMessage());
        }
        return result;
    }

    public ArrayList<String> getAll() {
        ArrayList<String> users = new ArrayList<>();
        String user;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "select * from users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String pk = rs.getString(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    user = "id:" + pk + ", " + "name" + name + ", " + "email" + email;
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка взаимодействия с базой данных: " + e.getMessage());
        }
        return users;
    }
};

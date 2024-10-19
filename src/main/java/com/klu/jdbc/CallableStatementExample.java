package com.klu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementExample {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/students_db";
        String username = "root";
        String password = "03Nov@2003123";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "{CALL get_student()}";
            CallableStatement statement = connection.prepareCall(sql);
            boolean hadResults = statement.execute();

            while (hadResults) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("sid");
                    String name = resultSet.getString("sname");
//                    int age = resultSet.getInt("age");
//                    String grade = resultSet.getString("grade");
                    System.out.println("ID: " + id + ", Name: " + name);
                }
                hadResults = statement.getMoreResults();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

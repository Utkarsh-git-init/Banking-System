package com.example.bankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static Connection getConnection() throws SQLException {
        String url="****";
        String user="postgres";
        String password="****";
        return DriverManager.getConnection(url,user,password);
    }

    public static void main(String[] args) throws SQLException {
        try (Connection conn =getConnection()){
            System.out.println("Connected to database");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

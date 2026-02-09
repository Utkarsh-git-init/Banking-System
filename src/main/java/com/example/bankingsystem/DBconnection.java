package com.example.bankingsystem;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection conn=null;
    private DBconnection(){};
    public static Connection getConnection() {
        if(conn==null){
            try{
                Dotenv dotenv=Dotenv.load();
                String url=dotenv.get("DB_URL");
                String user=dotenv.get("DB_USER");
                String password=dotenv.get("DB_PASSWORD");
                conn= DriverManager.getConnection(url,user,password);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        try (Connection conn =getConnection()){
            System.out.println("Connected to database");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

package com.example.bankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection conn=null;
    private DBconnection(){};

    public static Connection getConnection() {
        if(conn==null){
            try{
                String url="****";
                String user="postgres";
                String password="****";
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

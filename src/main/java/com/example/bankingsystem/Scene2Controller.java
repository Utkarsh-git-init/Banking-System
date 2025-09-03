package com.example.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scene2Controller {
    Connection conn=DBconnection.getConnection();
    public Label userid_label;
    public Label balance_label;
    public String userid;

    void show(String userid) throws SQLException {
        this.userid=userid;
        String sql="SELECT balance FROM users WHERE user_id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,userid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String balance=rs.getInt("balance")+"";
        userid_label.setText(userid);
        balance_label.setText(balance);
    }
    public void transact(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene3.fxml"));
        AnchorPane root=loader.load();
        Scene3Controller controller3=loader.getController();
        controller3.setuserid(userid);
        Scene scene3=new Scene(root);
        HelloApplication.primarystage.setScene(scene3);
    }
    public void transaction_history() throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene4.fxml"));
        AnchorPane root=loader.load();
        Scene4Controller controller4=loader.getController();
        controller4.setuserid(userid);
        controller4.loadTransactions();
        Scene scene4=new Scene(root);
        HelloApplication.primarystage.setScene(scene4);
    }
    public void logout() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene1.fxml"));
        AnchorPane root=loader.load();
        Scene scene1=new Scene(root);
        HelloApplication.primarystage.setScene(scene1);
    }
}

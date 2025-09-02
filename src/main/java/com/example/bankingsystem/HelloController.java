package com.example.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private TextField user_id;
    @FXML
    private TextField password;
    public Label input_comment;
    Connection conn=DBconnection.getConnection();
    //public static String userid;
    public void signin(ActionEvent event) throws SQLException, IOException {
        String userid=user_id.getText();
        String passcode=password.getText();
        String sql="SELECT * FROM users WHERE user_id = ? AND password = ?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,userid);
        ps.setString(2,passcode);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            //this.userid=userid;
            switchToScene2(userid);
        }else{
            input_comment.setText("EITHER OF YOUR INFO IS WRONG");
        }
    }
    public void register(ActionEvent event) throws SQLException {
        String userid=user_id.getText();
        String passcode=password.getText();
        String sql="SELECT user_id FROM users WHERE user_id = ?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,userid);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            input_comment.setText("USER ID ALREADY EXISTS");
        }else{
            String sql2="INSERT INTO users (user_id, password, balance) VALUES (?, ?, ?)";
            PreparedStatement ps2=conn.prepareStatement(sql2);
            ps2.setString(1,userid);
            ps2.setString(2,passcode);
            ps2.setInt(3,0);
            ps2.executeUpdate();
        }
    }
    public void switchToScene2(String userid) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene2.fxml"));
        AnchorPane root=loader.load();
        Scene2Controller controller2=loader.getController();
        controller2.show(userid);
        Scene scene2=new Scene(root);
        HelloApplication.primarystage.setScene(scene2);
    }
}
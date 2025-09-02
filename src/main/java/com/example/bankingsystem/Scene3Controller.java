package com.example.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Scene3Controller {
    Connection conn=DBconnection.getConnection();
    public TextField amt;
    public TextField transfer_receivers_userid;
    public Label comment;
    public static String userid=Scene2Controller.userid;
    public int update(int amt,String userid) throws SQLException {
        String sql="UPDATE users SET balance=balance+? WHERE user_id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,amt);
        ps.setString(2,userid);
        return ps.executeUpdate(); // return number of rows affected
    }
    public void deposit(ActionEvent event) throws SQLException {
        int amount=Integer.parseInt(amt.getText());
        update(amount,userid);
        comment.setText("deposited amount "+amount+" to your account");
    }
    public void withdraw(ActionEvent event) throws SQLException {
        int amount=Integer.parseInt(amt.getText());
        update(-amount,userid);
        comment.setText("withdrew amount "+amount+" from your account");
    }
    public void transfer(ActionEvent event) throws SQLException {
        String rid=transfer_receivers_userid.getText(); //rid:receivers id
        int amount=Integer.parseInt(amt.getText());
        update(-amount,userid);
        update(amount,rid);
        comment.setText("transferred amount "+amount+" from your account to user "+rid);
    }
    public void back(ActionEvent event) throws SQLException, IOException {
        HelloController controller=new HelloController();
        controller.switchToScene2(userid);
    }
}

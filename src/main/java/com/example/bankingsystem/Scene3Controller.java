package com.example.bankingsystem;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scene3Controller {
    Connection conn=DBconnection.getConnection();
    public TextField amt;
    public TextField transfer_receivers_userid;
    public Label comment;
    public String userid;
    void setuserid(String userid){
        this.userid=userid;
    }
    public void update(int amt,String userid) throws SQLException {
        String sql="UPDATE users SET balance=balance+? WHERE user_id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,amt);
        ps.setString(2,userid);
        ps.executeUpdate(); // return number of rows affected
        String sql2="INSERT INTO transactions(user_id, amount) VALUES(?,?)";
        PreparedStatement ps2=conn.prepareStatement(sql2);
        ps2.setString(1,userid);
        ps2.setInt(2,amt);
        ps2.executeUpdate();
    }
    public boolean check(int amt) throws SQLException {
        String sql="SELECT balance FROM users where user_id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,userid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getInt("balance") - amt >= 0;
    }
    public void deposit(ActionEvent event) throws SQLException {
        int amount=Integer.parseInt(amt.getText());
        update(amount,userid);
        comment.setText("deposited amount "+amount+" to your account");
    }
    public void withdraw(ActionEvent event) throws SQLException {
        int amount=Integer.parseInt(amt.getText());
        if(check(amount)){
            update(-amount,userid);
            comment.setText("withdrew amount "+amount+" from your account");
        }else{
            comment.setText("insufficient balance");
        }
    }
    public void transfer(ActionEvent event) throws SQLException {
        String rid=transfer_receivers_userid.getText(); //rid:receivers id
        int amount=Integer.parseInt(amt.getText());
        if(check(amount)){
            update(-amount,userid);
            update(amount,rid);
            comment.setText("transferred amount "+amount+" from your account to user "+rid);
        }else{
            comment.setText("insufficient balance");
        }
    }
    public void back(ActionEvent event) throws SQLException, IOException {
        HelloController controller=new HelloController();
        controller.switchToScene2(userid);
    }
}

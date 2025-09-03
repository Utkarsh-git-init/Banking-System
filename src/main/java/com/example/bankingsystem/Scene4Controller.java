package com.example.bankingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class Scene4Controller {
    Connection conn=DBconnection.getConnection();
    String userid;
    void setuserid(String userid){
        this.userid=userid;
    }
    @FXML
    private TableView<Transactions> transaction_table;
    @FXML
    private TableColumn<Transactions,Integer> col_amt;
    @FXML
    private TableColumn<Transactions,String> col_type;
    @FXML
    private TableColumn<Transactions, Timestamp> col_time;
    private ObservableList<Transactions> observableList;
    @FXML
    public void initialize() throws SQLException {
        col_amt.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        observableList= FXCollections.observableArrayList();
        transaction_table.setItems(observableList);
    }
    public void loadTransactions() throws SQLException {
        String sql="SELECT * FROM transactions where user_id=? ORDER BY timestamp DESC";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,userid);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            String type_temp;
            if (rs.getInt("amount") < 0)
                type_temp = "DB";
            else
                type_temp = "CR";
            observableList.add(new Transactions(rs.getInt("amount"), type_temp, rs.getTimestamp("timestamp")));
        }
    }
    public void back(ActionEvent event) throws SQLException, IOException {
        HelloController controller=new HelloController();
        controller.switchToScene2(userid);
    }
}

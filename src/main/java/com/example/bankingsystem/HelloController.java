package com.example.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    public TextField user_id;
    @FXML
    private TextField password;
    public void signin(ActionEvent event){
        String userid=user_id.getText();
        String passcode=password.getText();
    }
    public void register(ActionEvent event){

    }
}
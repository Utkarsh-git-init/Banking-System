package com.example.bankingsystem;

import java.sql.Timestamp;

public class Transactions {
    private int amount;
    private String type;
    private Timestamp timestamp;
    public Transactions(int amount,String type,Timestamp timestamp){
        this.amount=amount;
        this.type=type;
        this.timestamp=timestamp;
    }
    public  int getAmount(){
        return amount;
    }
    public String getType(){
        return type;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
}

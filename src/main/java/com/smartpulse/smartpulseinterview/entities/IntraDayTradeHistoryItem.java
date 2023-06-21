package com.smartpulse.smartpulseinterview.entities;

import lombok.*;

@Data
public class IntraDayTradeHistoryItem {
    private long id;
    private String date;
    private String conract;
    private double price;
    private int quantity;


    //Concrat değeri PB ile başlıyorsa false PH ile başlıyorsa true döndürür
    public boolean contractPrefix(){
        return this.conract.startsWith("PH");
    }
}


package com.smartpulse.smartpulseinterview.entities;

import lombok.*;

@Data
public class IntraDayTradeHistoryResponse {
    private int resultCode;
    private String resultDescription;
    private IntraDayTradeHistoryList body;
}

package com.smartpulse.smartpulseinterview.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DataTable {
    private String date;
    private double totalQuantity;
    private double totalAmount;
    private double weightedAverage;

    public DataTable(String concrat, double totalQuantity, double totalAmount, double weightedAverage) {
        this.date = getDateFromConract(concrat);
        this.totalQuantity = doubleFormat(totalQuantity);
        this.totalAmount = doubleFormat(totalAmount);;
        this.weightedAverage = doubleFormat(weightedAverage);;
    }

    //concrat ın PH dan sonraki değerini Tarihe çevirir
    private String getDateFromConract(String concrat){
        String yearMonthDayHour = concrat.substring(2);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyMMddHH");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH.mm");

        return LocalDateTime.parse(yearMonthDayHour, inputFormatter).format(outputFormatter);
    }

    //Double sayının küsüratını düzenler
    private double doubleFormat(double value){
        value = Math.round(value * 100);
        value = value / 100;

        return value;
    }
}

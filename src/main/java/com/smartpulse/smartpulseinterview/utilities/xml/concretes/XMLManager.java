package com.smartpulse.smartpulseinterview.utilities.xml.concretes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpulse.smartpulseinterview.entities.IntraDayTradeHistoryResponse;
import com.smartpulse.smartpulseinterview.utilities.xml.abstracts.XMLService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class XMLManager implements XMLService {
    private static final String API = "https://seffaflik.epias.com.tr/transparency/service/market/intra-day-trade-history?endDate=2022-01-26&startDate=2022-01-26";

    //API dan XML verisini getirir
    @Override
    public String getXML() throws IOException {
        URL urlObj = new URL(API);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining());
        }
    }

    //XML verisini IntraDayTradeHistoryResponse objesi ile eşler
    @Override
    public IntraDayTradeHistoryResponse dataMapper(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data, IntraDayTradeHistoryResponse.class);
    }
}

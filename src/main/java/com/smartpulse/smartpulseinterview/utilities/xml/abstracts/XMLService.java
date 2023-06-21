package com.smartpulse.smartpulseinterview.utilities.xml.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartpulse.smartpulseinterview.entities.IntraDayTradeHistoryResponse;

import java.io.IOException;

public interface XMLService {
    String getXML() throws IOException;
    IntraDayTradeHistoryResponse dataMapper(String data) throws JsonProcessingException;
}

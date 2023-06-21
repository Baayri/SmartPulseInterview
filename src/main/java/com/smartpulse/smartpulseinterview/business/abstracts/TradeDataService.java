package com.smartpulse.smartpulseinterview.business.abstracts;

import com.smartpulse.smartpulseinterview.entities.DataTable;
import com.smartpulse.smartpulseinterview.entities.IntraDayTradeHistoryItem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TradeDataService {
    List<IntraDayTradeHistoryItem> getAll() throws IOException;
    Map<String, List<IntraDayTradeHistoryItem>> groupByContract() throws IOException;
    List<DataTable> calculateSummary() throws IOException;
}

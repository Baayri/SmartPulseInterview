package com.smartpulse.smartpulseinterview.business.concretes;

import com.smartpulse.smartpulseinterview.business.abstracts.TradeDataService;
import com.smartpulse.smartpulseinterview.entities.DataTable;
import com.smartpulse.smartpulseinterview.entities.IntraDayTradeHistoryItem;
import com.smartpulse.smartpulseinterview.entities.IntraDayTradeHistoryResponse;
import com.smartpulse.smartpulseinterview.utilities.xml.abstracts.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class TradeDataManager implements TradeDataService {

    @Autowired
    private XMLService xmlService;

    //Tüm concratları getirir
    @Override
    public List<IntraDayTradeHistoryItem> getAll() throws IOException {
        String xml = this.xmlService.getXML();
        IntraDayTradeHistoryResponse response = this.xmlService.dataMapper(xml);

        return response.getBody().getIntraDayTradeHistoryList();
    }

    //concratları Mapleyerek aynı değere sahip olanları gruplar
    @Override
    public Map<String, List<IntraDayTradeHistoryItem>> groupByContract() throws IOException {
        Map<String, List<IntraDayTradeHistoryItem>> groupedMap = new HashMap<>();
        for (IntraDayTradeHistoryItem item : this.getAll()) {
            if (item.contractPrefix()) {
                String contract = item.getConract();
                if (!groupedMap.containsKey(contract)) {
                    groupedMap.put(contract, new ArrayList<>());
                }
                groupedMap.get(contract).add(item);
            }
        }
        return groupedMap;
    }

    //Maplediği concratların içindeki datalar ile totalQuantity totalAmount weightedAverage değerlerini
    //daha sonra tablo haline getirebilmemiz için bir entity listesine atar
    @Override
    public List<DataTable> calculateSummary() throws IOException {

        List<DataTable> list = new ArrayList<>();

        for (Map.Entry<String, List<IntraDayTradeHistoryItem>> entry : this.groupByContract().entrySet()) {
            List<IntraDayTradeHistoryItem> groupList = entry.getValue();

            String concrat = entry.getKey();
            double totalQuantity = 0;
            double totalAmount = 0;

            for (IntraDayTradeHistoryItem item : groupList) {
                double price = item.getPrice();
                double quantity = item.getQuantity();

                totalQuantity += quantity / 10;
                totalAmount += price * quantity / 10;
            }

            double weightedAverage = totalAmount / totalQuantity;

            list.add(new DataTable(concrat,totalQuantity,totalAmount,weightedAverage));
        }

        list.sort(Comparator.comparing(DataTable::getDate));

        return list;
    }
}

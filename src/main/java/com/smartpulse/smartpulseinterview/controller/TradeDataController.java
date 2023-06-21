package com.smartpulse.smartpulseinterview.controller;

import com.smartpulse.smartpulseinterview.business.abstracts.TradeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class TradeDataController {

    @Autowired
    private TradeDataService tradeDataService;

    @GetMapping("/")
    public String getTradeData(Model model) throws IOException{
        model.addAttribute("data", this.tradeDataService.calculateSummary());
        return "index";
    }
}

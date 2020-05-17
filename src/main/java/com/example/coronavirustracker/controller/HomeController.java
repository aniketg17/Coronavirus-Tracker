package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.models.LocationsStats;
import com.example.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationsStats> allStats = coronavirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(LocationsStats::getLatestTotalCases).sum();
        model.addAttribute("locationStats", coronavirusDataService.getAllStats());
        model.addAttribute("totalReportedCases", totalCases);
        return "home";
    }
}
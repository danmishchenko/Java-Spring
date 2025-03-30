package edu.dp.sau.danmishchenko.controller;

import edu.dp.sau.danmishchenko.model.Smartphone;
import edu.dp.sau.danmishchenko.service.RozetkaService;
import edu.dp.sau.danmishchenko.service.ExcelService;
import edu.dp.sau.danmishchenko.service.PrivatBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse; // Correct import
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SmartphoneController {

    private final RozetkaService rozetkaService;
    private final PrivatBankService privatBankService;
    private final ExcelService excelService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currencyRate", privatBankService.getCurrencyRate());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<Smartphone> smartphones = rozetkaService.searchSmartphones(query);
        model.addAttribute("smartphones", smartphones);
        return "index";
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Smartphone> smartphones = rozetkaService.getAllSmartphones();
        excelService.exportToExcel(smartphones, response);
    }
}
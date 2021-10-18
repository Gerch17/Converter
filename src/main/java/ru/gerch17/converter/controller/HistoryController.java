package ru.gerch17.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gerch17.converter.entity.History;
import ru.gerch17.converter.entity.Valutes;
import ru.gerch17.converter.service.HistoryService;
import ru.gerch17.converter.service.ValuteService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @Autowired
    ValuteService valuteService;

    @GetMapping("/filter")
    public String filter(@RequestParam String outValuteHistory, @RequestParam String inValuteHistory, @RequestParam String calendar, Model model){
        java.util.Date normalDate = null;
        java.sql.Date sqlDate = null;
        try {
            normalDate = new SimpleDateFormat("yyyy-MM-dd").parse(calendar);
            sqlDate = new java.sql.Date(normalDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("valutes", valuteService.getValuteList());
        model.addAttribute("histories", historyService.getHistoryByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(inValuteHistory != "" && outValuteHistory != "" && sqlDate != null) model.addAttribute("histories", historyService.getByAll(outValuteHistory, inValuteHistory, sqlDate));
        else {
            if(inValuteHistory != "" && outValuteHistory != "") model.addAttribute("histories", historyService.getByOutIn(outValuteHistory, inValuteHistory));
            else if(inValuteHistory != "" && sqlDate != null) model.addAttribute("hisories", historyService.getByInCal(inValuteHistory,  sqlDate));
            else if(outValuteHistory != "" && sqlDate != null) model.addAttribute("histories", historyService.getByOutCal(outValuteHistory,  sqlDate));
            else if(outValuteHistory != "") model.addAttribute("histories", historyService.getHistoryByOutValute(outValuteHistory));
            else if(inValuteHistory != "") model.addAttribute("histories", historyService.getHistoryByInValute(inValuteHistory));
            else  model.addAttribute("histories", historyService.getHistoryByDate( sqlDate));
        }
        return "history";
    }
}


package ru.gerch17.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gerch17.converter.entity.Valutes;
import ru.gerch17.converter.repo.ValutesRepository;
import ru.gerch17.converter.service.HistoryService;
import ru.gerch17.converter.service.ValuteService;

@Controller
public class ConverterController {
    @Autowired
    ValuteService valuteService;
    @Autowired
    ValutesRepository valutesRepository;
    @Autowired
    HistoryService historyService;

    @GetMapping("/converter")
    public String calc(Model model){
        try {
            Valutes valutes = valutesRepository.findById(1);
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            if (!date.toString().equals(valutes.getDate().toString()))
            {
                valutesRepository.deleteAllInBatch();
                valuteService.getValutes();
            }
        }catch (Exception e)
        {
            valuteService.getValutes();
        }
        model.addAttribute("selectedOutValute", valutesRepository.findById(1).getValuteText());
        model.addAttribute("selectedInValute", valutesRepository.findById(1).getValuteText());
        model.addAttribute("valutes", valuteService.getValuteList());
        model.addAttribute("histories", historyService.getHistoryByUser(SecurityContextHolder
                .getContext().getAuthentication().getName()));
        return "converter";
    }

    @GetMapping("/submitConvert")
    public String convert(@RequestParam String outValute, @RequestParam String inValute, @RequestParam String inputData, Model model)
    {
            try {
                if(outValute != null) outValute = valutesRepository.findValutesByValuteText(outValute).getValute_name();
                else outValute = "Австралийский доллар";
            }catch (Exception e){ }
            try {
                if(inValute != null) inValute = valutesRepository.findValutesByValuteText(inValute).getValute_name();
                else inValute = "Австралийский доллар";
            }catch (Exception e) {}
        model.addAttribute("valutes", valuteService.getValuteList());
        model.addAttribute("inputData", inputData);
        model.addAttribute("selectedOutValute", valutesRepository.findValutesByValuteName(outValute).getValuteText());
        model.addAttribute("selectedInValute", valutesRepository.findValutesByValuteName(inValute).getValuteText());
        String check = valuteService.correctCheck(outValute, inValute, inputData);
        if(check != null)
        {
            model.addAttribute("incorrect", check);
            return "converter";
        }
        float fl = valuteService.counter(inputData, outValute, inValute);
        model.addAttribute("outputData", fl);
        historyService.addToHistory(SecurityContextHolder.getContext().getAuthentication().getName()
                                    ,valutesRepository.findValutesByValuteName(outValute).getValuteText()
                                    , valutesRepository.findValutesByValuteName(inValute).getValuteText()
                                    , Float.parseFloat(inputData)
                                    , fl
                                    , new java.sql.Date(new java.util.Date().getTime()));
        return "converter";
    }

    @GetMapping("/history")
    public String goToHistory(Model model){
        model.addAttribute("valutes", valuteService.getValuteList());
        model.addAttribute("histories", historyService.getHistoryByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "history";
    }

    @GetMapping("/logout")
    public String logouts(){
        return "logout";
    }


}
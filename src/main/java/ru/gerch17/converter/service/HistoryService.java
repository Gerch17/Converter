package ru.gerch17.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerch17.converter.entity.History;
import ru.gerch17.converter.repo.HistoryRepository;

import java.sql.Date;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    public void addToHistory(String user, String outValute, String inValute, float outValue, float inValue, Date date){
        History history = new History();
        history.setUserName(user);
        history.setOut_valute(outValute);
        history.setIn_valute(inValute);
        history.setOut_value(outValue);
        history.setIn_value(inValue);
        history.setDate(date);
        historyRepository.save(history);
    }

    public Iterable<History> getHistoryByUser(String user)
    {
        return historyRepository.findAllByUserName(user);
    }
    public Iterable<History> getHistoryByOutValute(String valute)
    {
        return historyRepository.findAllByOutValute(valute);
    }
    public Iterable<History> getHistoryByInValute(String valute)
    {
        return historyRepository.findAllByInValute(valute);
    }
    public Iterable<History> getHistoryByDate(Date date)
    {
        return  historyRepository.findAllByDate(date);
    }
    public Iterable<History> getByOutIn(String str1, String str2)
    {
        return historyRepository.findAllByInValuteAndOutValute(str2 ,str1);
    }
    public Iterable<History> getByOutCal(String str, Date date)
    {
        return historyRepository.findAllByOutValuteAndDate(str, date);
    }
    public Iterable<History> getByInCal(String str, Date date)
    {
        return historyRepository.findAllByInValuteAndDate(str, date);
    }
    public Iterable<History> getByAll(String str1, String str2, Date date){
        return historyRepository.findAllByOutValuteAndAndInValuteAndDate(str1, str2, date);
    }
}

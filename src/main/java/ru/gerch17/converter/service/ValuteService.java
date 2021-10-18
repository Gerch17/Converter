package ru.gerch17.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import ru.gerch17.converter.entity.Valutes;
import ru.gerch17.converter.repo.ValutesRepository;

import java.net.URL;
import java.text.DecimalFormat;

@Service
public class ValuteService {

    ValutesRepository valutesRepository;

    @Autowired
    public ValuteService(ValutesRepository valutesRepository) {
        this.valutesRepository = valutesRepository;
    }

    public void getValutes()
    {
        try{
            String pre_apiURL = "http://www.cbr.ru/scripts/XML_daily.asp";
            URL url = new URL(pre_apiURL);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(url.openStream());
            NodeList valutes = doc.getElementsByTagName("Valute");
            NodeList nodeDate = doc.getElementsByTagName("ValCurs");
            String date = nodeDate.item(0).getAttributes().getNamedItem("Date").getNodeValue();
            for(int i = 0; i < valutes.getLength(); i++)
            {
                Node nameValute = valutes.item(i);
                Element element = (Element) nameValute;
                Valutes valutes1 = new Valutes();
                valutes1.setId(i+1);
                valutes1.setValute_id(Integer.parseInt(getTagValue("NumCode", element)));
                valutes1.setChar_code(getTagValue("CharCode", element));
                valutes1.setValute_name(getTagValue("Name", element));
                valutes1.setValue(getTagValuefl("Value", element));
                valutes1.setValuteText("(" + getTagValue("CharCode", element) + ") " + getTagValue("Name", element));
                valutes1.setDate(date);
                valutesRepository.save(valutes1);
            }
            Valutes valutes1 = new Valutes();
            valutes1.setId(35);
            valutes1.setValute_id(643);
            valutes1.setChar_code("RUB");
            valutes1.setValute_name("Российский рубль");
            valutes1.setValue(10000 );
            valutes1.setDate(date);
            valutes1.setValuteText("(RUB) Российский рубль");
            valutesRepository.save(valutes1);
        }catch(Exception e){}
    }

    public String getTagValue(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    public int getTagValuefl(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        float f = Float.parseFloat(node.getNodeValue().replaceAll(",", "."));
        int value = (int) (f * 10000);
        return value;
    }

    public Iterable<Valutes> getValuteList(){
        return valutesRepository.findAll();
    }

    public String correctCheck(String inValute, String outValute, String value)
    {
        if(inValute.equals(outValute)) return "Выберите разные валюты";
        try{
            float f = Float.parseFloat(value);
            if (f == 0) return "Вы не можете обменять нулевое значение";
        }catch (Exception e){
            return "Некорректный ввод";
        }
        return null;
    }

    public float counter(String input, String firstValute, String secondValute)
    {
        float fl = Float.parseFloat(input);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String str = decimalFormat.format((fl*(valutesRepository.findValutesByValuteName(firstValute).getValue()/10000))/(valutesRepository.findValutesByValuteName(secondValute).getValue()/10000));
        return Float.parseFloat(str.replaceAll(",", "."));
    }
}

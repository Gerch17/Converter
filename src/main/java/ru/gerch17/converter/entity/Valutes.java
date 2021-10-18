package ru.gerch17.converter.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="valutes")
public class Valutes {
    @Id
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="valute_id", nullable = false)
    private int valute_id;

    @Column(name="char_code", nullable = false)
    private String char_code;

    @Column(name="valute_name", nullable = false)
    private String valuteName;

    @Column(name="value", nullable = false)
    private int value;

    @Column(name="valute_text", nullable = false)
    private String valuteText;

    @Column(name="date", nullable = false)
    private java.sql.Date date;

    public String getValuteText() {
        return valuteText;
    }

    public void setValuteText(String valuteText) {
        this.valuteText = valuteText;
    }

    public Valutes(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChar_code() {
        return char_code;
    }

    public void setChar_code(String char_code) {
        this.char_code = char_code;
    }

    public String getValute_name() {
        return valuteName;
    }

    public void setValute_name(String valute_name) {
        this.valuteName = valute_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        String [] splittedDate = date.split("\\.");
        String normalDateString = splittedDate[2] + "-" + splittedDate[1] + "-" + splittedDate[0];
        Date normalDate = new SimpleDateFormat("yyyy-MM-dd").parse(normalDateString);
        java.sql.Date sqlDate = new java.sql.Date(normalDate.getTime());
        this.date = sqlDate;
    }

    public int getValute_id() {
        return valute_id;
    }

    public void setValute_id(int valute_id) {
        this.valute_id = valute_id;
    }
}

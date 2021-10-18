package ru.gerch17.converter.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="history")
public class History {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="out_valute", nullable = false)
    private String outValute;

    @Column(name="in_valute", nullable = false)
    private String inValute;

    @Column(name="out_value", nullable = false)
    private float outalue;

    @Column(name="in_value", nullable = false)
    private float inValue;

    @Column(name="date", nullable = false)
    private Date date;

    public History(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOut_valute() {
        return outValute;
    }

    public void setOut_valute(String out_valute) {
        this.outValute = out_valute;
    }

    public String getIn_valute() {
        return inValute;
    }

    public void setIn_valute(String in_valute) {
        this.inValute = in_valute;
    }

    public float getOut_value() {
        return outalue;
    }

    public void setOut_value(float out_value) {
        this.outalue = out_value;
    }

    public float getIn_value() {
        return inValue;
    }

    public void setIn_value(float in_value) {
        this.inValue = in_value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

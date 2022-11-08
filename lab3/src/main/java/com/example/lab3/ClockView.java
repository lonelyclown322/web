package com.example.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean
@ViewScoped
public class ClockView implements Serializable {

    private LocalDateTime dateTime = LocalDateTime.now();

    public void init() {
        dateTime = LocalDateTime.now();
    }

    public ClockView() {
        System.out.println("created");
    }

    public String getDateTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(dateTime.format(formatter));
        return dateTime.format(formatter);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
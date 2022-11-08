package com.example.lab3.coordinateBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class RBean implements Serializable {
    private boolean value1 = true;
    private boolean value2;
    private boolean value3;
    private boolean value4;
    private boolean value5;

    public double getValue() {
        if (value1) return 1;
        if (value2) return 1.5;
        if (value3) return 2;
        if (value4) return 2.5;
        if (value5) {
            return 3;
        } else return -1;
    }

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValue3() {
        return value3;
    }

    public void setValue3(boolean value3) {
        this.value3 = value3;
    }

    public boolean isValue4() {
        return value4;
    }

    public void setValue4(boolean value4) {
        this.value4 = value4;
    }

    public boolean isValue5() {
        return value5;
    }

    public void setValue5(boolean value5) {
        this.value5 = value5;
    }
}

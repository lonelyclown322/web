package com.example.lab3.coordinateBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class XBean implements Serializable {
    private boolean value1 = true;
    private boolean value2;
    private boolean value3;
    private boolean value4;
    private boolean value5;
    private boolean value6;
    private boolean value7;

    public byte getValue() {
        if (value1) return -5;
        if (value2) return -4;
        if (value3) return -3;
        if (value4) return -2;
        if (value5) return -1;
        if (value6) return  0;
        if (value7) {
            return 1;
        } else return -128;
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

    public boolean isValue6() {
        return value6;
    }

    public void setValue6(boolean value6) {
        this.value6 = value6;
    }

    public boolean isValue7() {
        return value7;
    }

    public void setValue7(boolean value7) {
        this.value7 = value7;
    }
}

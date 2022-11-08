package com.example.lab3;

import javax.persistence.*;


public class Hit {
    private double x;
    private double y;
    private double r;
    private boolean hitted;

    private int id;


    public Hit() {
    }

    public Hit(double x, double y, double r, boolean hitted) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitted = hitted;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean getHitted() {
        return hitted;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setHitted(boolean hitted) {
        this.hitted = hitted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

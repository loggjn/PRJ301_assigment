/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADC
 */
public class Cong_Month {
    private int congMonth;
    private int year;
    private int month;
    private Date NGAYTINHCONG;
    private int day;
    private boolean status ;

    public int getCongMonth() {
        return congMonth;
    }

    public void setCongMonth(int congMonth) {
        this.congMonth = congMonth;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Date getNGAYTINHCONG() {
        return NGAYTINHCONG;
    }

    public void setNGAYTINHCONG(Date NGAYTINHCONG) {
        this.NGAYTINHCONG = NGAYTINHCONG;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}

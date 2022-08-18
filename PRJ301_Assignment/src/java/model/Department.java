/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ADC
 */
public class Department {
    private int did;
    private String title;
    private ArrayList<Holiday> holiday;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Holiday> getHoliday() {
        return holiday;
    }

    public void setHoliday(ArrayList<Holiday> holiday) {
        this.holiday = holiday;
    }

    
    
    
}

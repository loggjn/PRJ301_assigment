/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import helper.DateTimeHelper;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ADC
 */
public class Employee {
    private int eid;
    private String ename;
    private String department;
    
    private ArrayList<TimeSheet> timesheets = new ArrayList<>();
    private ArrayList<RequestForLeave> requests = new ArrayList<>();
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<TimeSheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(ArrayList<TimeSheet> timesheets) {
        this.timesheets = timesheets;
    }

    public ArrayList<RequestForLeave> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<RequestForLeave> requests) {
        this.requests = requests;
    }
    public int fullSlot(){
        int sum =0;
        for (int i = 0; i < timesheets.size()-1; i++) {
            if(timesheets.get(i).getCidate().equals(timesheets.get(i+1).getCidate())){
                if (timesheets.get(i).getSlot().getSid() == 1){
                    if(timesheets.get(i+1).getSlot().getSid() == 2){
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
    
    public int haftSlot(){
        return timesheets.size()-fullSlot()*2;
    }
    
    public int Phep(){
        int sum =0;
        for (RequestForLeave rl : requests) {
            if (rl.getReason() == 1){
                int begin = DateTimeHelper.getDayOfMonth(rl.getFrom());
                int end = DateTimeHelper.getDayOfMonth(rl.getTo());
                sum+= end - begin +1;
            }
        }
        return sum;
    }
    
    public int CongTac(){
        int sum =0;
        for (RequestForLeave rl : requests) {
            if (rl.getReason() == 2){
                int begin = DateTimeHelper.getDayOfMonth(rl.getFrom());
                int end = DateTimeHelper.getDayOfMonth(rl.getTo());
                sum+= end - begin +1;
            }
        }
        return sum;
    }
    
    public int Om(){
        int sum =0;
        for (RequestForLeave rl : requests) {
            if (rl.getReason() == 3){
                int begin = DateTimeHelper.getDayOfMonth(rl.getFrom());
                int end = DateTimeHelper.getDayOfMonth(rl.getTo());
                sum+= end - begin +1;
            }
        }
        return sum;
    }
    public int ThaiSan(){
        int sum =0;
        for (RequestForLeave rl : requests) {
            if (rl.getReason() == 4){
                int begin = DateTimeHelper.getDayOfMonth(rl.getFrom());
                int end = DateTimeHelper.getDayOfMonth(rl.getTo());
                sum+= end - begin +1;
            }
        }
        return sum;
    }
    
    public float Sum(){
        return fullSlot() + haftSlot()/2;
        
    }
    
}

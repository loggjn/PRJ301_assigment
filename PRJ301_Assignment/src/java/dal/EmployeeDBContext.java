/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import helper.DateTimeHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.RequestForLeave;
import model.Slot;
import model.TimeSheet;

/**
 *
 * @author ADC
 */
public class EmployeeDBContext extends DBContext{
  public ArrayList<Employee> getEmps (Date from, Date to){
        ArrayList<Employee> emps = new ArrayList<>();
        try {
          String sql = "SELECT e.eid,e.ename,d.did,(d.title) Department , ISNULL(t.tid,-1) tid,\n" +
"                    t.checkin,t.checkout,s.sid, s.name ,t.hid ,h.title\n" +
"                    FROM Employee e LEFT JOIN \n" +
"                    (SELECT * FROM TimeSheet WHERE checkin >= ?\n" +
"                    AND checkin <= ?) t\n" +
"                    ON e.eid = t.eid\n" +
"					LEFT JOIN Department d on d.did = e.did\n" +
"					LEFT JOIN Slot s on s.sid = t.slot_id\n" +
"					LEFT JOIN Holiday h on h.hid = t.hid";
          PreparedStatement stm = connection.prepareStatement(sql);
          stm.setTimestamp(1, DateTimeHelper.getTimeStamp(from));
          stm.setTimestamp(2, DateTimeHelper.getTimeStamp(to));
          ResultSet rs = stm.executeQuery();
          Employee currentEmp = new Employee();
          currentEmp.setEid(-1);
          while (rs.next())
          {
                int eid = rs.getInt("eid");
                if (eid != currentEmp.getEid())
                {
                  currentEmp = new Employee();
                  currentEmp.setEid(eid);
                  currentEmp.setEname(rs.getString("ename"));
                  currentEmp.setDepartment(rs.getString("Department"));
                  emps.add(currentEmp);
                  
                }
                int tid = rs.getInt("tid");
                if(tid !=-1)
                {
                    TimeSheet t = new TimeSheet();
                    t.setTid(tid);
                    t.setCheckin(rs.getTimestamp("checkin"));
                    t.setCheckout(rs.getTimestamp("checkout"));
                    Slot slot =new Slot();
                    slot.setSid(rs.getInt("sid"));
                    
                    t.setSlot(slot);
                    
                    t.setEmployee(currentEmp);
                    currentEmp.getTimesheets().add(t);
                }
                
          } 
      } catch (SQLException ex) {
          Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
      }
      return emps;
  }
  
  
    public ArrayList<RequestForLeave> getRequests(Date from, Date to, int eid)
    {
        ArrayList<RequestForLeave> requests = new ArrayList<>();
        try {
            String sql ="SELECT rid\n" +
"                       ,eid\n" +
"                       ,[From]\n" +
"                       ,[to]\n" +
"                       ,type_id\n" +
"                       , tr.title\n" +
"                       FROM (SELECT * FROM RequestForLeave WHERE [From] >= ?\n" +
"                       AND [From] <= ? and eid = ? and status = '1')rl LEFT JOIN TypeRequest tr on type_id = tr.trid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, DateTimeHelper.getTimeStamp(from));
            stm.setTimestamp(2, DateTimeHelper.getTimeStamp(to));
            stm.setInt(3, eid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                RequestForLeave r = new RequestForLeave();
                r.setRid(eid);
                Employee e = new Employee();
                e.setEid(rs.getInt("eid"));
                r.setEmployee(e);
                r.setReason(rs.getInt("type_id"));
                r.setFrom(rs.getTimestamp("From"));
                r.setTo(rs.getTimestamp("To"));
                requests.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return requests;
    }
}

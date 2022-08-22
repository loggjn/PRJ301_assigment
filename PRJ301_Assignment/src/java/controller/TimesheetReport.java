/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.EmployeeDBContext;
import helper.DateTimeHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Employee;
import model.RequestForLeave;
import model.TimeSheet;

/**
 *
 * @author ADC
 */
public class TimesheetReport extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
     
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Date today = new Date();
        today = DateTimeHelper.removeTime(today);
        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
        Date begin = DateTimeHelper.addDays(today, (dayOfMonth-1)*-1);
        Date end1 = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1),0);
        Date end2 = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1),-1);
        EmployeeDBContext db = new EmployeeDBContext();
        ArrayList<Employee> emps_data = db.getEmps(begin, end1);
        for (Employee e : emps_data) {
            e.setRequests(db.getRequests(begin, end1, e.getEid()));
        }
        
        ArrayList<Date> dates = DateTimeHelper.getDates(begin, DateTimeHelper.addDays(end2, 0));
//        emps.get(1).setRequests(db.getRequests(begin, end,emps.get(1).getId()));
        request.setAttribute("emps", emps_data);
        request.setAttribute("dates", dates);
        request.setAttribute("month", (DateTimeHelper.getMonth(begin)+1));
        request.setAttribute("year", DateTimeHelper.getYear(begin));
        
        request.getRequestDispatcher("views/report.jsp").forward(request, response); 
    } 
    

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

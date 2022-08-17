<%-- 
    Document   : Cong_Month
    Created on : Aug 14, 2022, 1:40:55 AM
    Author     : ADC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    <script>
            //function checkinput(){
    //	var  username = document.getElementById('valueInpu').value;
        //document.getElementById('results').innerText= username;
    //}
        const a ={
            x:function checkTime(){
                var today = new Date();
                var date = today.getFullYear(); 
                //document.getElementById('results').innerText = date;
        
                var date = Number(date);
       
            return date+0;	
        }
        }
    
        function validateSelectBox(obj){
                var options = obj.children;
                //var html = '';
                for (var i = 0; i < options.length; i++){
                    if (options[i].selected){
                       var html =options[i].value;
                    }
                }
                
                document.getElementById('results').innerHTML = html;
            }
    </script>
    </head>
    
        <table border="1px">
            <tr>
                <th>ID</th>
                <th>Mã Công Tháng</th>
                <th>Năm</th>
                <th>Tháng</th>
                <th>Ngày tính công</th>
                <th>Ngày công trong tháng</th>
                <th>Status</th>
            <tr/>
            <c:forEach items="${requestScope.requests}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.content}</td>
                    <td>${r.from}</td>
                    <td>${r.to}</td>
                    <td>${r.createdby.username}</td>
                <tr/>
            </c:forEach>
        </table>
        
        
            <form action="addCong_Month" method="POST">
                Year: <select name="year" id="year" >
                    <c:forEach begin="" end="2030" var="year">
                        <option value="${year}">${year}</option>
                    </c:forEach>
                    </select><br/>    
                Month: <select name="month" id="month" onchange="validateSelectBox(this)>
                <c:forEach begin="1" end="12" var="month">
                    <option value="${month}">${month}</option>
                </c:forEach>
            </select><br/>
            <div id="results"></div>
<!--                
          Ngày Tính Công: <select name="NGAYTINHCONG">
                
            </select>
            <input type="submit" value="addCong_Month" /> <br/>
          <a href="forget">Forget password?</a>
            -->
        
        </form>
    

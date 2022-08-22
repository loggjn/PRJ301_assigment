<%-- 
    Document   : report
    Created on : Aug 22, 2022, 11:39:31 AM
    Author     : ADC
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/report.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <jsp:useBean id="dth" class="helper.DateTimeHelper" />
        
        Bảng tháng ${requestScope.month} năm ${requestScope.year}
        <table border="1px">
        
            <tr>
                <td>Họ và tên</td>
                <td>Chức vụ</td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td
                        <c:if test="${dth.getDayOfWeek(d) eq 1 }">
                            class="sunday" 
                        </c:if>
                        <c:if test="${dth.getDayOfWeek(d) eq 7}">
                            class="sat" 
                        </c:if>
                        >
                        <fmt:formatDate pattern = "dd" 
                                        value = "${d}" /> <br/>
                        <fmt:formatDate pattern = "EEE" 
                                        value = "${d}" />
                    </td>
                </c:forEach>
                <td>Đủ Công</td>
                <td>Nửa Công</td>
                <td>Nghỉ Phép</td>
                <td>Công Tác</td>
                <td>Ốm</td>
                <td>Thai Sản</td>
                <td>Tổng Công</td>
            </tr>
            
            <c:forEach items="${requestScope.emps}" var="e">
                <tr>
                    <td>${e.ename}</td>
                    <td>${e.department}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td 
                            <c:if test="${dth.getDayOfWeek(d) eq 1}">
                                class="sunday" 
                            </c:if>
                            <c:if test="${dth.getDayOfWeek(d) eq 7}">
                                class="sat" 
                            </c:if>    
                            
                            >
                            <c:forEach items="${e.timesheets}" var="t"  varStatus="status">
                                <c:if test="${d eq t.cidate}">                                   
                                    <c:if test="${d ne e.timesheets[status.index-1].cidate}">
                                        <c:if test="${d eq e.timesheets[status.index+1].cidate}">
                                            X
                                        </c:if>
                                         <c:if test="${d ne e.timesheets[status.index+1].cidate}">
                                            X/2
                                            
                                        </c:if>
                                    </c:if>
                                    
                                </c:if>
                            </c:forEach>
                           <c:forEach items="${e.requests}" var="r">
                                <c:if test="${d >= r.from and d<=r.to}">
                                    <c:choose>
                                        <c:when test = "${r.reason eq 1}">
                                            P
                                        </c:when>
                                        <c:when test = "${r.reason eq 2}">
                                            H
                                        </c:when>
                                        <c:when test = "${r.reason eq 3}">
                                            O
                                        </c:when>      
                                        <c:when test = "${r.reason eq 4}">
                                            TS
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </c:forEach>

                        </td>
                    </c:forEach>
                    <td>${e.fullSlot()}</td>
                    <td>${e.haftSlot()}</td>
                    <td>${e.Phep()}</td>
                    <td>${e.CongTac()}</td>
                    <td>${e.Om()}</td>
                    <td>${e.ThaiSan()}</td>
                    <td>${e.Sum()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

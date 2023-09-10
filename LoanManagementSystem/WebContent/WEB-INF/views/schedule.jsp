<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List" import="com.entities.schedule" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMI Schedule</title>
</head>
<body>
<h1>EMI Schedule</h1>
<% List<schedule> ls = (List<schedule>) request.getAttribute("schedule"); %>
<table border="1">
<tr>
    <th>No Of Months</th>
    <th>EMI Per Month</th>
    <th>Rate Of Interest</th>
    <th>Total</th>
</tr>
<% for (schedule item : ls) { %>
<tr>
    <td><%= item.getMonths() %></td>
    <td><%= item.getEmiPerMonth() %></td>
    <td><%= item.getRateOfInterest() %></td>
    <td><%= item.getTotal() %></td>
</tr>
<% } %>
</table>
</body>
</html>

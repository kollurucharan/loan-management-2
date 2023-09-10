<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,com.entities.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

    <style>
        /* Custom CSS */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }

        h2 {
            color: #0077cc;
        }

        select {
            width: 150px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #0077cc;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e0e0e0;
        }

        input[type="submit"] {
            background-color: #0077cc;
            color: #fff;
            border: none;
            cursor: pointer;
            padding: 5px 10px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            background-color: #0077cc;
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
        }

        a:hover {
            background-color: #ff5500;
        }
    </style>
</head>
<body>
<%
	ArrayList<LoanApplicants> lapp=(ArrayList<LoanApplicants>)request.getAttribute("lapp");
    String selec=(String)request.getAttribute("sel");
%>
<center><h2>Admin Page</h2><br><br></center>
<form action="data" method="get">


<select name="sel" id="sel">
  <option value="all" <% if (selec == null || "all".equals(selec)) { %>selected<% } %>>all</option>
  <option value="lapp_cust_id" <% if ("lapp_cust_id".equals(selec)) { %>selected<% } %>>customer id</option>
  <option value="lapp_date" <% if ("lapp_date".equals(selec)) { %>selected<% } %>>date</option>
  <option value="lapp_amount_d" <% if ("lapp_amount_d".equals(selec)) { %>selected<% } %>>amount desc</option>
  <option value="lapp_amount_a" <% if ("lapp_amount_a".equals(selec)) { %>selected<% } %>>amount asc</option>
</select>

<input type="submit" value="filter">
</form>
<table border=1>
<tr>
<th>loan applicant id</th>
<th>customer id</th>
<th>nominee id</th>
<th>applied date</th>
<th>loan type id</th>
<th>loan amount</th>
<th>emi range from</th>
<th>emi range to</th>
<th>emi months</th>
<th>cibil score</th>
<th>annual income</th>
<th>dispose income</th>
<th>status</th>
<th>conclusion remarks</th>
<th>edit</th>
</tr>
<%
	for(LoanApplicants lp:lapp){
%>
<form action="schedule" method="get">
<tr>
<td><%=lp.getLapp_id() %></td>
<td><%=lp.getLapp_cust_id() %></td>
<td><%=lp.getLapp_lnom_id() %></td>
<td><%=lp.getLapp_date() %></td>
<td><%=lp.getLapp_ltype_id() %></td>
<td><%=lp.getLapp_amount() %></td>
<td><%=lp.getLapp_emirange_from() %></td>
<td><%=lp.getLapp_emirange_to() %></td>
<td><%=lp.getLapp_months_req() %></td>
<td><%=lp.getLapp_cibil_score() %></td>
<td><%=lp.getLapp_annual_income() %></td>
<td><%=lp.getLapp_dispos_income() %></td>
<td><%=lp.getLapp_status() %></td>
<td><%=lp.getLapp_conclusion_remarks() %></td>
<input type="hidden" name="lapp_id" value="<%=lp.getLapp_id()%>">
<td>
                <% if (!"processing".equals(lp.getLapp_status())) { %>
                    <input type="submit" value="Schedule">
                <% } else { %>
                    <button disabled>Schedule</button>
                <% } %>
            </td>
</tr>
</form>
<%} %>
</table>
<button onclick="download()">Downlaod Excel</button>

<script>
function download(){
	var x=document.getElementById("sel").value;
	console.log(x);
	window.location.href="downloadExcel?sel="+x;
}
</script>
</body>
</html>
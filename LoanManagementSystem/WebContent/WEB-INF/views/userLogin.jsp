<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
<%String incorrect=(String)request.getAttribute("false");%>
<h1><%if(incorrect!=null){%>incorrect crediantials<%} %></h1>
    <h1>User Login</h1>
    <form action="startpage" method="get">
        <label for="id">ID:</label>
        <input type="text" id="id" name="userName" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="passWord" name="passWord" required><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>

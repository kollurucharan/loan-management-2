<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form action="startpage" method="get">
        <label for="id">ID:</label>
        <input type="text" id="id" name="userName" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="passWord" required><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>

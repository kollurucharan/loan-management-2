<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Button Event Example</title>
</head>
<body>
<center>
<h1>Select Your Login</h1>
    <button id="userLoginButton">User Login</button><br><br>
    <button id="adminButton">Admin Login</button>

</center>
    <script>
        // Function to handle the click event for the User Login button
        document.getElementById("userLoginButton").addEventListener("click", function () {
           window.location.href="userLogin";
            // You can add your login logic here
        });

        // Function to handle the click event for the Password button
        document.getElementById("adminButton").addEventListener("click", function () {
        	 window.location.href="adminLogin";
            // You can add your password logic here
        });
    </script>
</body>
</html>

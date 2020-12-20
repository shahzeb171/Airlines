<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN LOGIN PAGE</title>
</head>
<body>
<h1>ADMIN LOGIN PAGE</h1>

<% if(session.getAttribute("aFiltered")!=null && (Boolean)session.getAttribute("aFiltered")) { %>
<h4>USE ONLY CHARACTERS OR NUMBERS</h4>
<% session.removeAttribute("aFiltered"); } %>

<% if(session.getAttribute("failedLoginAdmin")!=null && (Boolean)session.getAttribute("failedLoginAdmin")) { %>
<h4>USERNAME OR PASSWORD INCORRECT</h4>
<% session.removeAttribute("failedLoginAdmin"); } %>



<form action="adminLogin" method="post">

<input type="text" placeholder="USERNAME" name="username" required> 
<input type="password" placeholder="PASSWORD" name="password" required>
<button type="submit">Login!</button>
</form>

<a href="index">GO TO INDEX PAGE -></a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AIRLINE RESERVATION</title>
</head>
<body>	
<% if(session.getAttribute("loggedinUser")!=null && (Boolean)session.getAttribute("loggedinUser")){%>
<h1>WELCOME TO AIRLINES USER ${name}</h1>

<% session.removeAttribute("loggedinUser");} %>

<% if(session.getAttribute("loggedinAsAdmin")!=null && (Boolean)session.getAttribute("loggedinAsAdmin")){%>
<h1>WELCOME TO AIRLINES ADMIN</h1>

<% session.removeAttribute("loggedinAsAdmin");} %>

<% if(session.getAttribute("loggedoutAdmin")!=null && (Boolean)session.getAttribute("loggedoutAdmin")){%>
<h1>GOODBYE ADMIN!</h1>

<% session.removeAttribute("loggedoutAdmin");} %>

<% if(session.getAttribute("loggedoutUser")!=null && (Boolean)session.getAttribute("loggedoutUser")){%>

<h1>GOODBYE USER ${name}!</h1>

<% session.removeAttribute("loggedoutUser");session.removeAttribute("name");} %>


<% if((session.getAttribute("loggedin") == null || !(Boolean)session.getAttribute("loggedin"))&&( session.getAttribute("loggedinAdmin") == null  || !(Boolean)session.getAttribute("loggedinAdmin"))){ %>
<a href="loginUser.jsp"><button>LOGIN USER</button></a>
<a href="loginAdmin.jsp"><button>LOGIN ADMIN</button></a>
<a href="signupUser.jsp"><button>SIGN UP</button></a>
<%  } else { %>
<a href="logout"><button>LOGOUT</button></a>
<% } %>

<% if(session.getAttribute("aFiltered")!=null && (Boolean)session.getAttribute("aFiltered")) { %>
<h4>BAD REQUEST ! RETRY</h4>
<% session.removeAttribute("aFiltered"); } %>



<% if(session.getAttribute("sameCity")!=null && (Boolean)session.getAttribute("sameCity")){%>

<h1>TO AND FROM CITY CANNOT BE SAME</h1>

<% session.removeAttribute("sameCity");} %>

<% if(session.getAttribute("noCity")!=null && (Boolean)session.getAttribute("noCity")){%>

<h1>TO OR FROM CITY NOT FOUND! RETRY</h1>

<% session.removeAttribute("noCity");} %>




<form action="search" method="post">
<label>FROM:
<input list="from" name="fromCity" required/></label>
<datalist id="from" >
 <c:forEach items="${Set}" var="s">
  <option value="${s}">
  </option>
  </c:forEach>
</datalist>
<label>TO:
<input list="to" name="toCity" required/></label>
<datalist id="to">
 <c:forEach items="${Set}" var="s">
  <option value="${s}">
  </option>
  </c:forEach>
</datalist>

<button type="submit">SEARCH</button>


<% if(session.getAttribute("loggedinAdmin")!=null && (Boolean)session.getAttribute("loggedinAdmin")){%>
<a href="add">GO TO ADMIN SETTINGS -></a>
<%} %>

</form>
</body>
</html>
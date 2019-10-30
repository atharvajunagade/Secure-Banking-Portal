<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-image:  url('resources/images/background-1.png');height:70%;width:100%">
 <%
 if(request.getSession(false).getAttribute("forgot")==null)
 {
 	response.sendRedirect("Login.jsp");
 }
    %>
<h4><label style="color:blue;">Sucess</label></h4>

<br/> <br/>
	
	<a href="LogoutUser">Logout</a>
</body>
</html>
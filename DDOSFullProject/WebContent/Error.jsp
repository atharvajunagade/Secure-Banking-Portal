<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
 <%
 if(request.getSession(false).getAttribute("uid")==null)
 {
 	response.sendRedirect("Login.jsp");
 }
 else
 {
    %>
<h5><%
String msg=request.getParameter("msg");
if(msg!=null)
	out.println("<span style='color:red'>"+msg+"</span>");
%></h5>
<%} %>
</body>
</html>


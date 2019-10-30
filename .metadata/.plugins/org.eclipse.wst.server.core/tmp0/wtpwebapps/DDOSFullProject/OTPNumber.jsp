<%-- <%@page import="com.mysql.jdbc.ResultSet"%> --%>
<%@page import="com.sendMail.SendMailBySite"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DatabaseConnection"%>
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
 else
 {
    %>
<center><%
String msg=request.getParameter("msg");
if(msg!=null)
	out.println("<span style='color:red'>"+msg+"</span>");
%>
</center>
<br><label style="color:blue;">Wait for SMS If you do not get SMS then check Email in Gmail account<br></label><br>
<br><label style="color:blue;">For your Security purpose Only:</label><br><br>
<br>
<form action="CkeckOTPNumber" method="post" name="otpnoform">
<div>
<input type="hidden" id="email" name="email" value="<%=request.getParameter("email") %>"><br><br>
<br><label style="color:blue ;">Enter OTP Code:<br><br>&nbsp;&nbsp;</label><input type="text" name="no" id="no" maxlength="5" pattern="[0-9]+" required="required">&nbsp;&nbsp;<label style="color:blue;"><br><br>It is a 5 digit Number</label><br><br>
<br><br><input type="submit" value="submit">
</div>
<%} %>
</form>
</body>
</html>
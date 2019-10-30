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
<center>
<%
String msg=request.getParameter("msg");
if(msg!=null)
	out.println("<span style='color:red'>"+msg+"</span>");
%>
</center>
<form action="SendSMSServlet" method="post" name="otpform">
<div>
<br>
<input type="hidden" name="email" value="<%=request.getParameter("email") %>">
<label style="color:blue;">Enter Mobile Number:<br><br>&nbsp;&nbsp;</label><input type="text" name="mobile" id="mobile" maxlength="10" pattern="[0-9]+" required="required"><label style="color:blue;">&nbsp;&nbsp;<br><br>Please Mention correct 10 digit Mobile Number</label><br>
<br><br><input type="submit" value=submit >
<%} %>

<br/> <br/>
	
	<a href="LogoutUser">Logout</a>
</div>
</form>
</body>
</html>
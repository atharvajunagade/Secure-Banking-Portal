<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<center>
<%
String msg=request.getParameter("msg");
if(msg!=null)
	out.println("<span style='color:red'>"+msg+"</span>");
%>
</center>
<form action="FundTransfer" method="post" name="otpform">
<div>
<br>
Enter Mobile Number:<input type="text" name="mobile" id="mobile" maxlength="10" pattern="[0-9]+" required="required">Please Mention correct 10 digit Mobile Number<br>
<br><br><input type="submit" value=submit >
<%} %>
</div>
</form>
</body>
</html>
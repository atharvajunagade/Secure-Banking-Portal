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
	<form action="CheckEmailServlet" method="post" name="otpform">
		<div>
			<br> Enter Email Address:<input type="email" id="email"
				name="email"><br>
			<br> &nbsp;&nbsp;<input type="submit" value="Continue">
		</div>
	</form>
	
	<br/> <br/>
	
	<a href="LogoutUser">Logout</a>
	<%} %>
</body>
</html>
<%@page import="com.AES.AESencrp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.dao.DB"%>

    <%@page import="java.util.ArrayList"%>
 <%
    //db db = new db();
   /*  ArrayList<Account> list = db.getData1();  */
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Net Banking</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<!-- stylesheets -->
		<link rel="stylesheet" type="text/css" href="resources\css\reset.css">
		<link rel="stylesheet" type="text/css" href="resources\css\style.css" media="screen">
		<link id="color" rel="stylesheet" type="text/css" href="resources\css\colors\blue.css">
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- scripts (jquery) -->
		<script src="resources\scripts\jquery-1.6.4.min.js" type="text/javascript"></script>
		<!--[if IE]><script language="javascript" type="text/javascript" src="resources/scripts/excanvas.min.js"></script><![endif]-->
		<script src="resources\scripts\jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
		<script src="resources\scripts\jquery.ui.selectmenu.js" type="text/javascript"></script>
		<script src="resources\scripts\jquery.flot.min.js" type="text/javascript"></script>
		<script src="resources\scripts\tiny_mce\tiny_mce.js" type="text/javascript"></script>
		<script src="resources\scripts\tiny_mce\jquery.tinymce.js" type="text/javascript"></script>
		<!-- scripts (custom) -->
		<script src="resources\scripts\smooth.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.menu.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.chart.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.table.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.form.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.dialog.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth.autocomplete.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				style_path = "resources/css/colors";

				$("#date-picker").datepicker();

				$("#box-tabs, #box-left-tabs").tabs();
			});
		</script>
	</head>
	<body>
		<!-- end dialogs -->
		<!-- header -->
		<div id="header">
			<!-- logo -->
			<div id="logo">
				<h1><a href="" title="Net Banking">Net Banking</a></h1>
			</div>
			<!-- end logo -->
			<!-- user -->
			<ul id="user">
				<!--  <li class="first"><a href="">Account</a></li> -->
				<li><i class="fa fa-sign-out fa-2x"></i><a href="LogoutUser">Logout</a>&nbsp;</li>
			</ul>
			<br>
			<br>
			<br>		
			</ul> 
			<!-- end user -->
			<div id="header-inner">
				<div id="home">
					<a href="" title="Home"></a>
				</div>
				<!-- quick -->
				<ul id="quick">
					<li>
						<a href="Admin.jsp" title="Products"><span class="normal">Home</span></a>
					</li>
					 
					<li>
						<a href="AdminContactUs.jsp" title="Products"><span>Contact Us</span></a>
					</li>
					 
				</ul>
				<!-- end quick -->
				<div class="corner tl"></div>
				<div class="corner tr"></div>
			</div>
		</div>
		<!-- end header -->
		<!-- content -->
		<div id="content">
			<!-- end content / left -->
			<div id="left">
				<div id="menu">
					<h6 id="h-menu-products" class="selected"><a href="Approve.jsp"><span>Approve/Reject Applicant</span></a></h6>
					<ul id="menu-products" class="opened">
						 
						
					</ul>
					<h6 id="h-menu-pages"><a href="CustomerList.jsp"><span>Customer List</span></a></h6>
					<ul id="menu-pages" class="closed">
						 
					</ul>
					 
					<h6 id="h-menu-links"><a href="SearchCustomer.jsp"><span>Search Customer</span></a></h6>
					<ul id="menu-links" class="closed">
						 
					</ul>
					<h6 id="h-menu-settings"><a href="SearchAccount.jsp"><span>Search Account</span></a></h6>
					<ul id="menu-settings" class="closed">
						 
					</ul>
				</div>
				<div id="date-picker"></div>
			</div>
			<!-- end content / left -->
			<!-- content / right -->
			<div id="right">
				<!-- table -->
				<div class="box">
					<!-- box / title -->
					 
							<div class="container">
						   <form action="" method="post">
							<table>
									<tr>
										<th align="center">Account Number</th>
										<th align="center">Customer Name</th>
										<th align="center">Contact Number</th>
										<th align="center">Balance</th>
									</tr>
									<%
									DatabaseConnection db1=new DatabaseConnection();
									String accountno=request.getParameter("accountno");
									
									String fullname="";
									String contact="";
									double balance=0;
									
									ResultSet rs=(ResultSet)session.getAttribute("search"); //Accessing the parameters from session
									
									
									try{
									if(rs!=null){
									while(rs.next())
									{
										 accountno=rs.getString("accountno");
										 fullname=AESencrp.decrypt(rs.getString("fullname"));
										 contact=AESencrp.decrypt(rs.getString("contact"));
										 balance=rs.getDouble("balance");
									%>
									<tr>
									<td align="center"><%=accountno%></td>
									<td align="center"><%=fullname%></td>
									<td align="center"><%=contact%></td>
									<td align="center"><%=balance%></td>
 								</tr><%} 
									}
									
									}
						catch(Exception e)
						{
								
								e.printStackTrace();
						}
						finally
						{
				    		
							try 
							{
							   if(rs!=null)
							   {
								rs.close();
								DatabaseConnection.closeConnection();
							   }
							} 
							catch(Exception e)
							{
									
									e.printStackTrace();
							}
						}	%>
							</table>		
							</div>
							</form>						
					</div>
				</div>												
				<!-- end box / right -->
			</div>
			<!-- end content / right -->
		</div>
		<!-- end content -->
		<!-- footer -->
		<div id="footer">
			<p>Copyright &copy; 2000-2010 Your Company. All Rights Reserved.</p>
		</div>
		<!-- end footert -->
	</body>
</html>
</body>
</html>
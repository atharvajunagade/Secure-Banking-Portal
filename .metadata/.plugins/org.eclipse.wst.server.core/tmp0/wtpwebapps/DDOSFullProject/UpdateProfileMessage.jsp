<%@page import="com.sun.org.apache.bcel.internal.generic.Select"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.dao.DB"%>
<%@page import="com.model.Account"%>
    <%@page import="java.util.ArrayList"%>
     <%
		 if(request.getSession(false).getAttribute("uid")==null)
		    {
		    	response.sendRedirect("Login.jsp");
		    }
		 else
		 {
    %>
 <%
 DB db = new DB();
   // ArrayList<Account> list = db.getData1(); 
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
			<!-- end user -->
			<div id="header-inner">
				<div id="home">
					<a href="" title="Home"></a>
				</div>
				<!-- quick -->
				<ul id="quick">
					<li>
						<a href="Home1.jsp" title="Products"><span class="normal">Home</span></a>
					</li>
					<li>
						<a href="#" title="Products"><span>About Us</span></a>
					</li>
					<li>
						<a href="SearchAccount.jsp" title="Products"><span>Contact Us</span></a>
					</li>
					<li>
						<a href="" title="Events"><span>Accounts</span></a>
						<ul>
						
							<li><a href="AccountDetails.jsp">Account Details</a></li>
							<li><a href="MiniStatement.jsp">Mini Statements</a></li>
							
						</ul>
					</li>
					<li>
						<a href="" title="Pages"><span>Transaction</span></a>
						<ul>
							<li><a href="FundTransfer.jsp">Transfer Money</a></li>
							<li><a href="BillPayment.jsp">Bill Payments</a></li>
						</ul>
					</li>
					<li>
						<a href="" title="Settings"><span>Account Settings</span></a>
						<ul>
							<li><a href="UpdateProfile.jsp">Update Your Profile</a></li>
							
						</ul>
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
				
				<div>
			<img src="resources/images/bank.jpg" style="width: 220px;height: 665px; margin-left:60px">
			</div>
				<div id="date-picker"></div>
			</div>
			<!-- end content / left -->
			<!-- content / right -->
			
			  <div id="right">
				<!-- table -->
				<div class="box">
					<!-- box / title -->
					<div class="title">
						<h5>Update Your Profile</h5>
					</div>
					<!-- end box / title -->
					<div class="table">
				<div class="inner">
			</div>
				<form method="get" action="">
				<div class="form">					
				<div class="field">
					<table>
						<h5 align="center" style="font-style: normal">Your Profile has been changed successfully</h5><br><br>
					</table>						
				</div>
				</div>
				</form>
				</div>
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
		<%} %>
	</body>
</html>
</body>
</html>
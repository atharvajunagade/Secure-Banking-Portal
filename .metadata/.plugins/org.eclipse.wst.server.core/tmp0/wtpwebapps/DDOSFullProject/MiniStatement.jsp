<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.MiniState"%>
<%@page import="com.util.DatabaseConnection"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.List"%>
<%@page import="com.model.MiniStatement"%>
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
    
 <% MiniState ms= new MiniState(); // Creating the object of MiniState
 String accountnumber =  session.getAttribute("accountno").toString();
  DatabaseConnection db = new DatabaseConnection();
 ResultSet rs = db.selectQuery("SELECT * FROM ebank.user where accountno='"+accountnumber+"'");
 
 double availableBalance = 0.0;
 
 try{
	 
 if(rs.next());
  availableBalance = rs.getDouble("balance"); //Fetching the customers available balance from database.
 
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
		}	
		
 ArrayList<MiniStatement> list = ms.getDetails(accountnumber); //Passing the accountno to getDetails method of MiniState class.
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
						<a href="Home1.jsp" title="Products"><span class="normal">Home</span></a>
					</li>
					
					<li>
						<a href="ContactUs.jsp" title="Products"><span>Contact Us</span></a>
					</li>
					<li>
						<a href="" title="Events"><span>Accounts</span></a>
						<ul>
						
							<li><a href="AccountDetails.jsp?uid=<%=session.getAttribute("uid")%>">Account Details</a></li> 
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
						
						<!-- <div class="search">
							 
						</div> -->
					</div>
					<!-- end box / title -->				
					<div class="table" >
						<form action="" method="post">
						<table style="width: 1067px";>
							<tr>
								<th>Account Number &nbsp:&nbsp<%=accountnumber%><br><br>
								Available Balance &nbsp:&nbsp<%=availableBalance%></th>
							</tr>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</table>
						
						<table>
							<tr>
								<th align="left">Transaction ID</th>
								<th align="left">Transaction Type</th>
								<th align="left">Amount</th>
								<th align="left">Date and Time</th>
							</tr>
							
					<%
						for(int i=0;i<list.size();i++) 
						{
							MiniStatement ms1=list.get(i); //Accessing the transaction details of customer.
							System.out.println(ms1);
							out.print("<tr><td>"+ms1.getTransId()+"</td><td>"+ms1.getTransType()+"</td><td>"+ms1.getTransAmount()+"</td><td>"+ms1.getTransTime()+"</td></tr>");
						}

					%>
						</table>					
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
		<%} %>
	</body>
</html>
</body>
</html>
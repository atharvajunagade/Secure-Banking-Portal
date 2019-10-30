<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.AES.AESencrp"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.Select"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.dao.DB"%>
<%@page import="com.model.Account"%>
    <%@page import="java.util.ArrayList"%>
    
     <%
 
    

		
		int uid=0;
		String struid="";
		DatabaseConnection dbc;
		String strurl="URL Injection";	
		String ipaddress="";
				try
				{
				if(session.getAttribute("uid")==null)
				{
				System.out.print("session null");				
				dbc=new DatabaseConnection(); 
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //This is for Current Date and Time.
				Calendar calobj = Calendar.getInstance();
				String dt=dateFormat.format(calobj.getTime());		
				//ip=request.getRemoteAddr();
				System.out.println(dt);
				//ipaddress=InetAddress.getLocalHost().getHostAddress();//Find the IPAddress for Current PC/Laptop etc
				
				HttpServletRequest requestHttp = (HttpServletRequest) request;
							 
							String urlString = requestHttp.getRequestURL().toString();
					// get IP Address of client from request
							if(urlString.contains("localhost"))
								ipaddress = request.getLocalAddr();
							
							else
								ipaddress = request.getRemoteAddr();
					
					
				System.out.println("IP Addresss:"+ipaddress);
				int a=dbc.updateQuery(" insert into ebank.admin(logs,time,ipaddress) values('"+strurl+"','"+dt+"','"+ipaddress+"')");			
				session.invalidate(); //Invalidating the session for URL Injection.				
					 %>
					<jsp:forward page="Login.jsp?msg=URL Injection"></jsp:forward>				
					<%
			}
			else
				{			    
					uid = Integer.parseInt(session.getAttribute("uid").toString());		
					System.out.println(uid);
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		%>
     
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
			<script type="text/javascript">
			function act(val)
			{
				if(val==1)
				{
					$("#mobilefield").show();
					$("#amountfield").show();
					$("#policynofield").hide();
					$("#customernofield").hide();
					$("#remarksfield").show();
				
				} 
				if(val==2) 
				{
					$("#mobilefield").hide();
					$("#amountfield").show();
					$("#policynofield").hide();
					$("#customernofield").show();
					$("#remarksfield").show();
				}
				if(val==3) 
				{
					$("#mobilefield").hide();
					$("#amountfield").show();
					$("#policynofield").show();
					$("#customernofield").hide();
					$("#remarksfield").show();
				}
			}
			function hideAll()
			{
				$("#mobilefield").hide();
				$("#amountfield").hide();
				$("#policynofield").hide();
				$("#customernofield").hide();
				$("#remarksfield").hide();
			}
			</script>
	</head>
	<body onload="hideAll()">
		
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
						<h5>Fund Transfer</h5>
					</div>
					<!-- end box / title -->
					<div class="table">
				<div class="inner">
			</div>
				<form method="get" action="BillPayments" name="myform">
				<div class="form">
					<!-- fields -->
					<div class="fields">
						 
						<div class="field">
							<div class="label">
								<label for="bill_type">Bill Type</label>
							</div>
						<div class="input">
						<select id="billtype" name="billtype" style="width: 166px;height: 27px;" onchange="act(this.value);">
						<option value="0">Select</option>
						<option value="1">Mobile Recharge</option>
						<option value="2">Electricity Bill</option>
						<option value="3">Insurance</option>
						</select>
						</div>
						</div>
						
						<div class="field" id="mobilefield">
							<div class="label">
								<label for="mobno" name="mobno" >Mobile Number</label>
							</div>
							<div class="input">
								<input type="text" id="mobileno" maxlength="10" name="mobileno" size="40" value="" class="focus" onkeypress="return isNumberKey(event)">
							</div>
						</div>
						<div class="field" id="customernofield" >
							<div class="label">
								<label for="custno" name="custno" >Customer Number</label>
							</div>
							<div class="input">
								<input type="text" id="customerno" name="customerno" size="40" value="" class="focus" onkeypress="return isNumberKey(event)" maxlength="10">
							</div>
						</div>
						<div class="field" id="policynofield" >
							<div class="label">
								<label for="polno" name="polno">Policy Number</label>
							</div>
							<div class="input">
								<input type="text" id="policyno" name="policyno" size="40" value="" class="focus" >
							</div>
						</div>
						<div class="field" id="amountfield" >
							<div class="label">
								<label for="amt" name="amt">Amount</label>
							</div>
							<div class="input">
								<input type="text" id="amount" maxlength="5" name="amount" size="40" value="" class="focus" onkeypress="return isNumberKey(event)">
							</div>
						</div>
						<div class="field" id="remarksfield" >
							<div class="label">
								<label for="remarks" name="remarks">Remarks</label>
							</div>
							<div class="input">
								<input type="text" id="remarks" name="remarks" size="40" value="" class="focus">
								
								
								
							</div>
						</div>																	
						<div class="buttons">
							<input type="submit" value="OK" >
							
							
          
						</div>
						
						<br/> <br/>
											 <%
             String str = request.getParameter("message");
             if(str!=null)
             {
               
          %>
          
          <h2 style="color : crimson;"><%= str.replaceAll("_", " ") %></h2>
          
          <%
          
          
          
             }
          %>
          
					</div>
					<!-- end fields -->
					<!-- links -->
					
					<!-- end links -->
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
		
		<SCRIPT language=Javascript>
     function isNumberKey(evt)
{
    	 
    	 var charCode = (evt.which) ? evt.which : event.keyCode;
    	 
    	 

    	 if (charCode > 31 && ((charCode < 48) || (charCode > 57)))
    	 	return false;
    	 	
    	 return true;
}


</SCRIPT>


	</body>
</html>
</body>
</html>
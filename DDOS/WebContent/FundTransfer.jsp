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
 DB db = new DB();
    

		
		int uid=0;
		String struid="";
		DatabaseConnection dbc;
		String str="URL Injefction";	
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
				int a=dbc.updateQuery(" insert into ebank.admin(logs,time,ipaddress) values('"+str+"','"+dt+"','"+ipaddress+"')");			
				session.invalidate(); //Invalidating the session for URL Injection.				
					%>
					<jsp:forward page="Login.jsp"></jsp:forward>				
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
    	 
    	 int accNo = Integer.parseInt(session.getAttribute("accountno").toString());
    %>
 
    
<!DOCTYPE html>
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
						<h5>Fund Transfer</h5>
					</div>
					<!-- end box / title -->
					<div class="table">
				<div class="inner">
			</div>
				<form method="post" action="FundTransfer" name = "transaction">
				<div class="form">
					<!-- fields -->
					<div class="fields">
						
						<div class="field">
							<div class="label">
								<label for="receiver_account">Benificiary Account Number</label>
							</div>
							<div class="input">
						<select id="raccountno" name="raccountno" style="width: 166px;height: 27px;" required>
						<%
						DatabaseConnection db1=new DatabaseConnection();
					     uid=(Integer)session.getAttribute("uid");
						ResultSet rs=db1.selectQuery("select accountno from user");
						
						try{
						while(rs.next())
						{
							int raccountno=rs.getInt("accountno");
							
							if(raccountno != accNo)
							{
							
						%>
							<option value="<%=raccountno%>"><%=raccountno%></option>
							<%
							
							}
							
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
								
							   }
							   DatabaseConnection.closeConnection();
							} 
							catch(Exception e)
							{
									
									e.printStackTrace();
							}
						}	
							%>	
						</select>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label for="benificiary_name">Benificiary Name</label>
							</div>
							<div class="input">
								<input title="e.g. abc xyz def" type="text" id="benificiary_name" required pattern="[a-zA-Z]+\s[a-zA-Z]+\s[a-zA-Z]+" name="benificiary_name" size="40" value="" class="focus">
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="amount">Transaction Amount</label>
							</div>
							<div class="input">
								<input type="text" title="e.g. 12000" pattern="[0-9]+" onkeypress="return isNumberKey(event)" required maxlength="10" id="amount" name="transAmount" size="40" value="" class="focus">
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="pin">Transaction Password(Pin)</label>
							</div>
							<div class="input">
								<input title="e.g. 1243" type="text" id="pin" required name="pin" onkeypress="return isNumberKey(event)" maxlength="5" size="40" value="" class="focus">
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="remarks">Remarks</label>
							</div>
							<div class="input">
								<input title="e.g. Room Rent" type="text" id="remarks" required name="remarks" size="40" value="" class="focus" pattern = "[a-zA-z\s]+">
							</div>
						</div>										
						<div class="buttons">
							<input type="submit" value="OK" onclick="checkData();">
						</div>
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
		
		
		
		
	</body>
	
	
	


	<script type="text/javascript">

function isValidDate(s) 
{
	  var bits = s.split('-');
	  var y = bits[2], m  = bits[1], d = bits[0];
	  // Assume not leap year by default (note zero index for Jan)
	  var daysInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];

	  // If evenly divisible by 4 and not evenly divisible by 100,
	  // or is evenly divisible by 400, then a leap year
	  if ( (!(y % 4) && y % 100) || !(y % 400)) {
	    daysInMonth[1] = 29;
	  }
	  return d <= daysInMonth[--m]
}

</script>

 
    
    <SCRIPT language=Javascript>
     function isNumberKey(evt)
{
var charCode = (evt.which) ? evt.which : event.keyCode;

if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;
	
return true;
}

</SCRIPT>
</html>
</body>
</html>
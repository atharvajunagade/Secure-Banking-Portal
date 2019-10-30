<%@page import="com.AES.AESencrp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//This is for Remember password.

	Cookie[] cookies = request.getCookies();
	//HttpServletRequest
	boolean foundCookie = false;
	String username = null;
	String password = null;
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i]; //Creating the object of Cookie
			if (c.getName().equals("username")) {
				username = AESencrp.decrypt(c.getValue());
				foundCookie = true;
			}
			if (c.getName().equals("password")) {
				password = AESencrp.decrypt(c.getValue());
				foundCookie = true;
			}
		}
	}
	if (foundCookie == false) {
		username = "";
		password = "";
	}
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>DDOS Prevention in E-Banking</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css"
	media="screen">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
<script src="js/jquery.js"></script>
<style>
.test {
	width: 200px;
}
</style>


<script type="text/javascript">
var x = document.getElementById("demo");

function getLocation() {
    if (navigator.geolocation) {
    	
        navigator.geolocation.getCurrentPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
 
	
	   $.ajax({
	        type: "get", 		//GET or POST or PUT or DELETE verb
	        url: "SaveData", 		// Location of the service
	        data: {lat:position.coords.latitude,lng:position.coords.longitude}, 		//Data sent to server
	       
	        processdata: true, 	//True or False
	        success: function (json) 
	        {
	        	//On Successful service call
	        	$("#loginform").submit();
	        	 
	        }
	        
	    });
   

//     window.location.assign("SaveData?lat="+position.coords.latitude+"&lng="+position.coords.longitude+"");
}
</script>


<script src="resources/scripts/jquery-1.6.4.min-1.js"
	type="text/javascript"></script>
<script src="resources/scripts/jquery-ui-1.8.16.custom.min-1.js"
	type="text/javascript"></script>
<script src="resources/scripts/smooth-1.js" type="text/javascript"></script>
<script type="text/javascript">
			$(document).ready(function () {
				style_path = "resources/css/colors";
				$("input.focus").focus(function () {
					if (this.value == this.defaultValue) {
						this.value = "";
					}
					else {
						this.select();
					}
				});

				$("input.focus").blur(function () {
					if ($.trim(this.value) == "") {
						this.value = (this.defaultValue ? this.defaultValue : "");
					}
				});

				$("input:submit, input:reset").button();
			});
		</script>
<script type="text/javascript">
			function access(){ 
	        	<%if (request.getSession().getAttribute("msg") != null) {

				String str = request.getSession().getAttribute("msg").toString();%>           
	            alert('notice'+" : "+"<%= str %>
	");
<%request.getSession().setAttribute("msg", null);
			} else {%>
	
<%}%>
	}
</script>
</head>
<!-- <body onload="access()" style="background-image: url('resources/images/b.jpg')"> -->

<center>
	<%
		String msg = request.getParameter("msg");
		System.out.println("msg" + msg);
		if (msg != null)
			out.println("<span style='color:red'>" + msg + "</span>");
	%>

</center>
</head>
<body onload="access()"
	style="background-image: url('dist/img/3003.jpg'); background-repeat: no-repeat; height: 100%; width: 100%; background-size: contain; background-position: center; background-attachment: fixed;">

	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-md-6">
			<!-- Horizontal Form -->
			<div class="box box-info"
				style="margin-left: -150px; border-top-color: brown;">
				<div class="box-header with-border">
					<h3 class="box-title">Sign In To Net-Banking</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal" id="loginform" method="post"
					action="LoginUser1">
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Username</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" id="username"
									name="username" required="required" placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Password</label>

							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									name="password" required="required" placeholder="Password">
							</div>
						</div>

					</div>
					<%
						if (session.getAttribute("regStatus") != null) {
							out.print(session.getAttribute("regStatus"));
							session.setAttribute("regStatus", null);
						}

						if (session.getAttribute("error") != null) {
							out.println(session.getAttribute("error"));
							session.setAttribute("error", null);
						}
					%>
					<!-- /.box-body -->
					<div class="box-footer">


						<a href="ForgotServlet" style="color: brown;">Forgot your
							password?</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						<button type="submit" class="btn btn-info test"
							style="background-color: brown;" onclick="getLocation();">Sign
							in</button>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="Registration.jsp"
							style="color: brown;">Registration for New User</a>
					</div>
					<!-- /.box-footer -->
				</form>
			</div>
			<!-- /.box -->
		</div>
		<div class="col-md-3"></div>
	</div>
	</section>

	<!-- ./wrapper -->

	<!-- jQuery 2.2.3 -->
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
</body>
</html>
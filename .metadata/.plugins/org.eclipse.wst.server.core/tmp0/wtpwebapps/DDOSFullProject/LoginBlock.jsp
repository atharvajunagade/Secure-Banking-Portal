<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>Net Banking Login</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<!-- stylesheets -->
		<link rel="stylesheet" type="text/css" href="resources\css\reset-1.css">
		<link rel="stylesheet" type="text/css" href="resources\css\style-1.css" media="screen">
		<link id="color" rel="stylesheet" type="text/css" href="resources\css\colors\blue-1.css">
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- scripts (jquery) -->
		<script src="resources\scripts\jquery-1.6.4.min-1.js" type="text/javascript"></script>
		<script src="resources\scripts\jquery-ui-1.8.16.custom.min-1.js" type="text/javascript"></script>
		<script src="resources\scripts\smooth-1.js" type="text/javascript"></script>
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
			  <% 
			  //This is for showing Error message to user after entering wrong username and password
			  if(request.getAttribute("error")!=null)
			  {
			  String str=request.getAttribute("error").toString();%>
			  var err="<%=str %>";
			  var s=document.getElementById("message-error");
			   	s.style.visibility="visible";
			   	document.getElementById("text").innerHTML =err;
			 <%}%>
			} 
		</script>
	</head>
	<body onload="access()">
		<div id="login">
			<!-- login -->
			<div class="title">
				<h5>Sign In to Net Banking</h5>
				<div class="corner tl"></div>
				<div class="corner tr"></div>
			</div>
			<div class="messages">
				<div id="message-error" class="message message-error" style="visibility: hidden;">
					<div class="image">
						<img src="resources\images\icons\error-1.png" alt="Error" height="32">
					</div>
					<div >
						<h6>Error Message</h6>
						<span id="text"></span>
					</div>
					<div class="dismiss">
						<a href="#message-error"></a>
					</div>
				</div>
			</div>
			<div class="inner">
				<form method="post" action="LoginUser1" id="actionForm">
				<div class="form">
					<!-- fields -->
					<div class="fields">
						
						<div class="field" style="align-content: center;">
							<div class="field">	
								<h5 >You Are blocked For Today</h5>
							</div>
						</div>						
						<div class="buttons">
						</div>
						<br>
					</div>				
				</div>
				</form>
			</div>
			
		</div>
	</body>
</html>
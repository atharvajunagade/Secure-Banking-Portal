<%@page import="com.AES.AESencrp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>DDOS Prevention in E-Banking</title>
  <link rel="stylesheet" type="text/css" href="resources/css/style.css" media="screen">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  
  <style>
  .test{
    
    width:200px;
}

</style>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <script src="resources/scripts/jquery-1.6.4.min-1.js" type="text/javascript"></script>
		<script src="resources/scripts/jquery-ui-1.8.16.custom.min-1.js" type="text/javascript"></script>
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
			  <% 
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
	<!-- <body onload="access()" style="background-image: url('resources/images/b.jpg')"> -->
	
	<center><%
String msg=request.getParameter("msg");
if(msg!=null)
	out.println("<span style='color:red'>"+msg+"</span>");
%>

</center>
</head>
<body style="background-image: url('dist/img/3003.jpg');background-repeat: no-repeat;height:100%;
   	width:100%;
	background-size: contain;
  	background-position: center;
  	background-attachment: fixed;">
  	
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-6">
          <!-- Horizontal Form -->
          <div class="box box-info" style="margin-left: -150px;border-top-color: brown;">
            <div class="box-header with-border">
              <h3 class="box-title">Net-Banking</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
            
            <font color="crimson" size="5">You are not allowed to access system with your current IP Address</font>
            
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
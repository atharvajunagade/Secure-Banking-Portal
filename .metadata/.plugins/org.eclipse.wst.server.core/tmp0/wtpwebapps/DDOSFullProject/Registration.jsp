<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DDOS Prevention in E-Banking</title>
 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>E-Banking</title>
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
<link rel="stylesheet" href="bootstrap/css/datepicker.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
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
 
<body onload="access()" style="background-image: url('dist/img/4004.jpg');background-repeat: no-repeat;height:108px;
   	width:100%;
	background-size: cover;
  	background-position: center;
  	background-attachment: fixed;">
  <div class="wrapper">
  	 <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo" style="background-color: brown;">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>E-Banking</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" style="background-color: brown;">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

     
    </nav>
  </header>
    <!-- Main content -->
    <section class="content">
      <div class="row" style="margin-top: 20px;">
        <div class="col-md-6">
          <!-- Horizontal Form -->
           <div class="box box-primary" style="margin-left: -170px; border-top:brown; ">
            <div class="box-header with-border" >
          <center>    <h3 class="box-title" style="color: #367fa9;color: brown;"><b>Register Here</b></h3></center>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" name="myForm" method="post" action="RegisterServlet">
					<div class="box-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label foor="exampleInputEmail1">Full Name</label><span style="color: red">*</span>
								</div>
								<div class="col-md-8">
									<input type="text" id="fullName" class="form-control"
										name="fullName" required pattern="[a-zA-Z]+\s[a-zA-Z]+\s[a-zA-Z]+" style="float: left;"  onkeypress="return onlyAlphabets(event,this);" placeholder="e.g. aaa bbb ccc">
								</div>
							</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputPassword1">Father Name</label><span style="color: red">*</span> 
							</div>
							<div class="col-md-8">
							<input type="text" id="father" name="father" class="form-control"
								required pattern="[a-zA-Z]+\s[a-zA-Z]+\s[a-zA-Z]+" placeholder="e.g. bbb ddd ccc">
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputEmail1">Date of Birth</label><span style="color: red">*</span> 
							</div>
							<div class="col-md-8">
							<input
								type="date" id="bdate" name="dob" class="form-control" required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputPassword1">Mobile Number</label><span style="color: red">*</span> 
							</div>
							<div class="col-md-8">
							<input
								type="text" id="mobNo" name="contact" class="form-control"
								maxlength="10" pattern="[789][0-9]{9}" size="40" onkeypress="return isNumberKey(event)" placeholder="10 digits (start with 7/8/9)" required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputEmail1">Email ID</label><span style="color: red">*</span> 
							</div>
							<div class="col-md-8">
							<input
								type="email" id="emailId" name="email" class="form-control" placeholder="Valid Email Id"
								required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputPassword1">Pan Card No</label><span style="color: red">*</span>
							</div>
							<div class="col-md-8">
							 <input
								type="text" id="panNo" name="pan" class="form-control" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" maxlength="10" placeholder="e.g. ABCAD2233D" required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputEmail1">AAdhar Card No</label><span style="color: red">*</span> 
							</div>
							<div class="col-md-8">
							<input
								type="text" id="adharNo" name="aadhar" class="form-control" pattern="[0-9]{12}"  maxlength="12" onkeypress="return isNumberKey(event)" placeholder="12 Digits Aadhar Number"
								required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputPassword1">Country</label><span style="color: red">*</span>
							</div>
							<div class="col-md-8">
							 <input
								type="text" id="country" name="country" pattern="[a-zA-Z]+" placeholder="Minimum 3 Characters"  maxlength="3" onkeypress="return onlyAlphabets(event,this);"
								class="form-control" required>
						</div>
						</div>
						</div>
						
						<div class="form-group">
						<div class="row">
						<div class="col-md-4">
							<label for="exampleInputEmail1">Initial Balance</label><span style="color: red">*</span>
							</div>
							<div class="col-md-8">
							 <input
								type="text" id="initBal" name="initBal" pattern="[0-9]+" onkeypress="return isNumberKey(event)"
								class="form-control" maxlength="10" required>
						</div>
						</div>
						</div>
						<div class="checkbox"></div>
					</div>
					<!-- /.box-body -->

              <div class="box-footer">
              
                 &nbsp; &nbsp; &nbsp; &nbsp;
                 
              <a href="Login.jsp" class="btn btn-primary" style="float: left;background-color: brown;">Log In</a>
              
           
                <button type="submit" class="btn btn-primary" style="float: right;background-color: brown;">Submit</button>
              </div>
            </form>
          </div>
          <!-- /.box -->
    </div>
       <div class="col-md-3"></div>
    </div>
    </section>
    </div>
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
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
$('#bdate').datepicker();
</script>


	<script type="text/javascript">

  $(document).ready( function(){
	  
	  
	  $("input").blur(function(){
			  
		  
		  if($(this).val().trim() == "")
			  {
			  
			          alert("Form field cannot be empty");
			  
			  }
		  else
			  {
			     
			        var name =  $(this).attr("name");
			        
			       
			        
			       if(name == "email")
			        	{
			        	
			        	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; 
			        	
			        	if(!$(this).val().trim().match(mailformat))
			        		{
			        		 alert("You have entered an invalid email address!"); 
			        		 
			        		 $(this).val('');
			        		}
			        	
			        	}
			       else if(name == "country")
		        	{
			        	
			        	
			        	
			        	if(!($(this).val().trim().length > 2))
			        		{
			        		 alert("Length of Country name must be atleast 3!"); 
			        		 
			        		 $(this).val('');
			        		}
			        	
			        	}
			        
			  
			  
			  }
	  
	  
	  });
	  
	  
	  $("textarea").blur(function(){
		  
		  if($(this).val().trim() == "")
		  {
		  
		          alert("Form field cannot be empty");
		  
		  }
		  
	  });
	  
  });


  
  function isInt(value) {
	  return !isNaN(value) && 
	         parseInt(Number(value)) == value && 
	         !isNaN(parseInt(value, 10));
	}
	
  function hasWhiteSpaceandTab(s) {
	  return /\s/g.test(s);
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

 <script language="Javascript" type="text/javascript">

        function onlyAlphabets(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8 || ($("#fullName").val().length > 1 && charCode == 32))
                    return true;
                else
                    return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }

    </script>



</body>
</html>
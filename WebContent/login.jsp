<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/bootstrap-switch.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/signinandrego.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/datepicker.css" rel="stylesheet" media="screen">
    
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
   		<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
   		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   		<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
   		<script src="${pageContext.request.contextPath}/js/bootstrap-switch.min.js"></script>
   		<script src="${pageContext.request.contextPath}/js/custom/login_regoform.js"></script>
   		
    	
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <title>Login to App</title>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			$('#dp').datepicker();
		});
		$('#switch').bootstrapSwitch('setOnLabel', 'LOGIN');
		$('#switch').bootstrapSwitch('setOffLabel', 'REGO');
	</script>
	
	
	<div id="login" class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Email address" autofocus>
        <input type="password" class="form-control" placeholder="Password">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
    </div>
    
    <div id="rego" class="container">
      <form class="form-rego">
        <h2 class="form-rego-heading">Please Register</h2>
        <input type="text" class="form-control" placeholder="Email address" autofocus>
        <input type="text" class="form-control" placeholder="User Name" autofocus>
        
        <input type="password" class="form-control" placeholder="Password" style="width:200px">
        <input type="password" class="form-control" placeholder="Repeat Password" style="width:200px">
        
        <input type="text" class="form-control" placeholder="First Name" autofocus>
        <input type="text" class="form-control" placeholder="Last Name" autofocus>
        <input type="text" class="form-control" value="" placeholder="BirthDate" data-date-format="mm/dd/yy" id="dp" style="width:150px">
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form>
    </div>
    <div class="container">	
		<div class="switch-item">		
			<div id="switch" class="make-switch switch-large" data-on-label="LOGIN" data-off-label="REGO" style="height:40px">
		    	<input type="checkbox" checked>
			</div>
		</div>	
	</div>
    
    
    <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
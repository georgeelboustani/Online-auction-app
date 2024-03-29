<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" media="screen">
    
    <!-- custom css pages -->
    <link href="${pageContext.request.contextPath}/css/custom/login.css" rel="stylesheet" media="screen">
    
    <title>RollBack: Login Page</title>
</head>
<body>
	
	<div id="login" class="container">
      <div class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input id="formSigninUsername" type="text" class="form-control" placeholder="Username" autofocus>
        <input id="formSigninPassword" type="password" class="form-control" placeholder="Password">
        <div id="formSigninAlert"></div>
        <button id="loginSubmit" class="btn btn-lg btn-primary btn-block">Sign in</button>
      </div>
    </div>
    
    <div id="rego" class="container">
      
      <form class="form-rego" id="regoForm" method="get" action="">
        <h2 class="form-rego-heading">Please Register</h2>
        <br/>
        <input id="formRegoUsername" name="formRegoUsername" type="text" class="form-control" placeholder="User Name" autofocus />
        <input id="formRegoPassword" name="formRegoPassword" type="password" class="form-control" placeholder="Password" style="width:200px" />
        <input id="formRegoConfirmPassword" name="formRegoConfirmPassword" type="password" class="form-control" placeholder="Confirm Password" style="width:200px" />
        
        <br/>
        <h4 class="form-rego-heading">Personal Info</h4>
        <input id="formRegoNickName" name="formRegoNickName" type="text" class="form-control" placeholder="Nick Name" autofocus />
        <input id="formRegoFirstName" name="formRegoFirstName" type="text" class="form-control" placeholder="First Name" autofocus />
        <input id="formRegoLastName" name="formRegoLastName" type="text" class="form-control" placeholder="Last Name" autofocus />
        <input id="formRegoEmail" name="formRegoEmail" type="email" class="form-control" placeholder="Email address" autofocus />
        <input id="formRegoBirthDate" name="formRegoBirthDate" type="text" class="form-control" value="" placeholder="BirthDate" data-date-format="mm/dd/yy" id="dp" style="width:150px" />
        <input id="formRegoAddress" name="formRegoAddress" type="text" class="form-control" placeholder="Address" autofocus />
        
        <br/>
        <h4 class="form-rego-heading">Credit Details</h4>
        <input id="formRegoCreditCardNo" name="formRegoCreditCardNo" type="text" class="form-control" placeholder="Credit Card No." autofocus />
        
        <div id="formRegoAlert"></div>
      	<button id="regoSubmit" type="submit" class="btn btn-lg btn-primary btn-block">Register</button>
      </form>
    </div>
    
    <div class="switch-container">
	   	<div>
			<div id="choice">
			 	<input type="radio" id="choice1" name="radio" checked="checked" /><label for="choice1">Login</label>
				<input type="radio" id="choice2" name="radio" /><label for="choice2">Register</label>
			</div>
		</div>
	</div>
    
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed JS script files at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-switch.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/additional-methods.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!-- custom scripts -->
	<script src="${pageContext.request.contextPath}/js/custom/login_rego.js"></script>
</body>
</html>
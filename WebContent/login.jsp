<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/signinandrego.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" media="screen">
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
   		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   		<script src="${pageContext.request.contextPath}/js/bootstrap-switch.min.js"></script>
   		<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
   		
   		<script src="${pageContext.request.contextPath}/js/custom/login_regoform.js"></script>
   		
    <title>Login to App</title>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			
			$( "#choice" ).buttonset();
			
			$('#dp').datepicker({
			    beforeShow: function(input, inst)
			    { inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'}); }
			});
			
			$('#rego').hide();
			
			$('#choice').click(function(){
				if($('#choice1').is(':checked')){
					$('#login').show();
					$('#rego').hide();
				} 
				if($('#choice2').is(':checked')) {
					$('#login').hide();
					$('#rego').show();
				}
			});
		});
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
    
    <div class="switch-container">
	   	<form>
			<div id="choice">
			 	<input type="radio" id="choice1" name="radio" checked="checked" /><label for="choice1">Login</label>
				<input type="radio" id="choice2" name="radio" /><label for="choice2">Register</label>
			</div>
		</form>
	</div>
    
    
    <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
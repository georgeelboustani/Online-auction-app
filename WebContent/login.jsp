</html><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/static/js/respond.min.js"></script>
    <![endif]-->
    <title>Login to Application</title>
</head>
<body>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   	<script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
   	<script src="${pageContext.request.contextPath}/static/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
   	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    
    <h2>Hello, please log in:</h2><br/>
    <form method=post name="callback" id="loginForm" >
		<p>
		<strong>UserName:</strong>
		<input type="text"  name= "username" />
		</p>
		<p>
		<strong>Password:</strong>
   		<input type="password"  name= "password" />
	    </p>
	    <input type="submit" value="Submit"/>
	</form>
</body>
</html>
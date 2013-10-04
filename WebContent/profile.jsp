<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userDTO" class="jdbc.UserDTO" scope="session" />    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/signinandrego.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/custom/index.css" rel="stylesheet" media="screen">
    
    <!-- custom -->
    <link href="${pageContext.request.contextPath}/css/custom/profile.css" rel="stylesheet" media="screen">
    <title>RollBack: Profile Page</title>
</head>
<body>
	<div class="container">
		<!-- Navbar -->
		<jsp:include page="navbar.jsp"/>
		
		  	<form class="form-profile" id="profileForm" method="post" action="">
		    	<div class="col-md-4 well form-profile-block">
			    	<div class="formProfileBlock" ><span id="formProfileHeader" class="form-profile-heading-main">Le Profile</span><button id="profileUpdate" type="submit" class="btn btn-primary btn-block">Update</button></div>
			        <br/>
			        <p>User Name: <strong>${userDTO.username}</strong></p>
			        <input id="formProfileEmail" name="formProfileEmail" type="text" class="form-control" value="${userDTO.email}" placeholder="Email" autofocus />
			        <input id="formProfilePassword" name="formProfilePassword" type="text" class="form-control"  placeholder="Password" autofocus />
			        <input id="formProfileConfirmPassword" name="formProfileConfirmPassword" type="text" class="form-control" placeholder="Confirm Password" autofocus />
		        </div>
		        <div class="col-md-4 well">
			        <h4 class="form-profile-heading">Personal Info</h4>
			        <br/>
			        <input id="formProfileNickName" name="formProfileNickName" type="text" class="form-control" value="${userDTO.nickname}" placeholder="Nick Name" autofocus />
			        <input id="formProfileFirstName" name="formProfileFirstName" type="text" class="form-control" value="${userDTO.firstName}" placeholder="First Name" autofocus />
			        <input id="formProfileLastName" name="formProfileLastName" type="text" class="form-control" value="${userDTO.lastName}" placeholder="Last Name" autofocus />
			        <input id="formProfileBirthDate" name="formProfileBirthDate" type="text" class="form-control" value="${userDTO.yearOfBirth}" placeholder="BirthDate" data-date-format="mm/dd/yy" id="dp" style="width:150px" />
			        <input id="formProfileAddress" name="formProfileAddress" type="text" class="form-control" value="${userDTO.address}" placeholder="Address" autofocus />
		        </div>
		        <div class="col-md-4 well">
			        <h4 class="form-profile-heading">Credit Details</h4>
			        <br/>
			        <input id="formProfileCreditCardNo" name="formProfileCreditCardNo" type="text" class="form-control" value="${userDTO.creditCardNum}" placeholder="Credit Card No." autofocus />
			        
			        <div id="formProfileAlert"></div>
			      	
			    </div>
		     </form>
  		
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
	<script src="${pageContext.request.contextPath}/js/custom/profile.js"></script>
</body>
</html>
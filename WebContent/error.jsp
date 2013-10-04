<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/custom/index.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/custom/index_auction.css" rel="stylesheet" media="screen">
   	
    <title>RollBack: 404 Error page</title>
</head>
<body>
    
    <div class="container">
    	
    
	    <div class="col-md-6">
	    	<button onclick="window.location='controller?action=searchAuction'">Back to index page</button>
	    	<p><h1>404 Page Not Found.</h1></p>
	    	<p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
	    	<p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p><br />
	    </div>
	    <div class="col-md-6">
	    	<img src="http://i.imgur.com/Y1h46.gif" alt="okay" height=500px/>
	    </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed JS script files at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-switch.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
	
	<!-- custom scripts -->
	
</body>
</html>
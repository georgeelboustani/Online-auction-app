<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/custom/index.css" rel="stylesheet" media="screen">
   		
    <title>RollBack: Auction Page</title>
</head>
<body>
	<div class="container">
      <!-- Static navbar -->
      <div class="navbar navbar-default">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${pageContext.request.contextPath}">RollBack Auctions</a>
        </div>
          <ul class="nav navbar-nav navbar-right">
          	
            <li class="active">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Auctions <b class="caret"></b></a>
				<ul class="dropdown-menu">
		            <li><a href="#">All Auctions</a></li>
		            <li><a href="#">Bids</a></li>
		            <li><a href="#">Selling</a></li>
		            <li class="divider"></li>
		            <li class="dropdown-header"></li>
		            <li><a href="#">Purchase History</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">User Profile</a></li>
                <li><a href="#">Change Passwords</a></li>
              </ul>
            </li>
            <li><a href="../navbar-fixed-top/">Logout</a></li>
          </ul>
        </div>
  	
		<!-- Main component for a primary marketing message or call to action -->
	    <div class="jumbotron">
	      <h1>Navbar example</h1>
	      <p>This example is a quick exercise to illustrate how the default, static navbar and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
	      <p>
	        <a class="btn btn-lg btn-primary" href="../../components/#navbar">View navbar docs &raquo;</a>
	      </p>
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
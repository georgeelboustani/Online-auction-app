<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="auctionDTO" class="jdbc.AuctionDTO" scope="session" />    
<jsp:useBean id="searchBean" class="pagebeans.SearchBean" scope="session" />
	
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
    <link href="${pageContext.request.contextPath}/css/custom/auction.css" rel="stylesheet" media="screen">
   		
    <title>RollBack: Auction Page</title>
</head>
<body>
	
	<div class="container">
		<!-- Navbar - from navbar.jsp -->
		<jsp:include page="navbar.jsp"/>
		
		<!-- content holder for left auctions -->
		<div class="col-md-9 well">
	 		<div class="col-md-3">		
	 			<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
		    </div>
		    <div class="col-md-9">
				<p><h3>Title Title Title</h3></p>
		        <p><span class="description-title">Description:</span><br/><span class="text">Description Description Description</span></p>
		        <p><span class="description-title">Postage:</span><br/><span class="text">Postage Postage Postage</span></p>   	
		        <p>Current Bid:<br/><strong>100.00</strong></p>
		        <p>Time Left:<br/><strong>15 mins</strong></p>
		         	
		        <div id="auction-bid" class="input-prepend auction-input">
				  	<span class="add-on">$</span>
				  	<input class="span2 form-control bid-input" id="appendedPrependedInput" type="text">
				  	<button id="auction-bid-button" type="button" class="btn btn-sm btn-primary">Bid</button>
				</div>
			</div>
  		</div><!-- /end left auction panel -->
  		
  		<!-- Sidebar - from Sidebar.jsp -->
  		<jsp:include page="sidebar.jsp"/>
      	
	</div><!-- /end container -->
	
	
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="auctionDTO" class="jdbc.AuctionDTO" scope="session" />    
<jsp:useBean id="sellingBean" class="pagebeans.SellingBean" scope="session" />

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
    
    <link href="${pageContext.request.contextPath}/css/custom/index_auction.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/custom/my_bid.css" rel="stylesheet" media="screen">
   		
    <title>RollBack: My Bids</title>
</head>
<body>
	<div class="container">
		<!-- Navbar -->
		<jsp:include page="navbar.jsp"/>
		
		<!-- content holder for left auctions -->
  		<div class="col-md-9">
  			
  			<div class="jumbotron">
  				<h2>Something to Sell?</h2>
  				<div class="col-md-6">
  					<input id="formTitle" type="text" class="form-control" placeholder="Title" autofocus />
  					<input id="formImage" type="file" class="form-control" placeholder="Upload Image" autofocus />
  					
  				</div>
  				
  				<div class="col-md-6">
  					<span style="display:inline; font-size:15px;">Category: </span>
  					<select class="form-control" style="width:250px; display:inline;">
  						<option>Select</option>
  						<option>Furniture</option>
  						<option>Fashion</option>
  						<option>Books</option>
  					</select>
  					<input id="formImage" type="file" class="form-control" placeholder="Upload Image" autofocus />
  				</div>
  			</div>
  			
  			<!-- auction content -->
	  		<div class="list-group">
	            <c:choose>
					<c:when test="${sellingBean.displayError}">
						${sellingBean.errorMessage}
					</c:when>
					
					<c:otherwise>
						<c:set var="count" value="0" scope="page" />
			           	<c:forEach var="auction" items="${sellingBean.auctions}" >	
				            <a href="#" class="list-group-item row">
				            	<div class="col-md-3 list-group-item-text">
				            		<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
				            	</div>
				            	<div class="col-md-4 list-group-item-text">
				            		<h4>${auction.auctionTitle}</h4>
				            		<p>${auction.auctionDescription}</p>
				            	</div>
				            	<div class="col-md-2 list-group-item-text">
				            		<p>Current Bid:<br/><strong>${sellingBean.bids[count]}</strong></p>
				            		<p>Time Left:<br/><strong>${sellingBean.times[count]} mins</strong></p>
				            	</div>
				            </a>
			            	<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</c:otherwise>
				</c:choose>
        	</div>
  			
  		</div><!-- end auction placeholder -->
  		
		<!-- Sidebar - from Sidebar.jsp -->
  		<jsp:include page="sidebar.jsp"/>
      	
	</div><!-- /container -->
	
	
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="auctionDTO" class="jdbc.AuctionDTO" scope="session" />    
<jsp:useBean id="myBidBean" class="pagebeans.MyBidBean" scope="session" />

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
  			
  			<!-- auction content -->
	  		<div class="list-group">
	            
	    		<c:choose>
					<c:when test="${myBidBean.displayError}">
						${myBidBean.errorMessage}
					</c:when>
					
					<c:otherwise>
						<c:set var="count" value="0" scope="page" />
			           	<c:forEach var="auction" items="${myBidBean.auctions}" >	
				            <a href="#" class="list-group-item row">
				            	<div class="col-md-3 list-group-item-text">
				            		<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
				            	</div>
				            	<div class="col-md-4 list-group-item-text">
				            		<h4>${auction.auctionTitle} <c:if test="${myBidBean.highestBidder[count]}"> {Current Bid Leader}</c:if></h4>
				            		<p>${auction.auctionDescription}</p>
				            	</div>
				            	<div class="col-md-2 list-group-item-text">
				            		<p>Current Bid:<br/><strong>${myBidBean.bids[count]}</strong></p>
				            		<p>Time Left:<br/><strong>${myBidBean.times[count]} mins</strong></p>
				            	</div>
				            	<div class="col-md-3 list-group-item-text">
				            		<p>
				            			<!--  
				            			<div class="alert alert-danger my-bid-alert">
					            			<p><span><strong>Failed Bid:</strong> $3.00</span></p>
					            		</div>
					            		<div class="alert alert-success my-bid-alert">
					            			<p><span><strong>Winning Bid:</strong> $4.00</span></p>
					            		</div> 
					            		-->
				            			<div class="input-prepend auction-input">
										  <span class="add-on">$</span>
										  <input class="span2 form-control bid-input" id="appendedPrependedInput" type="text">
										  <button type="button" class="btn btn-sm btn-primary">Bid</button>
										</div>
				            		</p>
								</div>
			            		<p>
			            			<div class="input-prepend auction-input">
										<button type="button" id="auction-details-button" class="btn btn-sm btn-primary auction-details-button" onclick="getDetails(${auction.aid}); return false;">Show Details</button>
									</div>
			            		</p>
				            </a>
			            	<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
				<!-- nav bar between 10 auctions -->
	  			<div class="row auction-nav-bar">
	  				<div class="col-md-9"></div>
	  				<div class="col-md-3">
	  					<p class="next-prev-nav-item">
		  					<button type="button" class="btn btn-warning">
		  						<span class="ui-icon ui-icon ui-icon-circle-triangle-w"></span>
		  					</button>
		  					<button type="button" class="btn btn-warning">
		  						<span class="ui-icon ui-icon ui-icon-circle-triangle-e"></span>
		  					</button>
		  				</p>
	  				</div>
	  			</div>
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
	<script src="${pageContext.request.contextPath}/js/custom/my_bid.js"></script>
</body>
</html>
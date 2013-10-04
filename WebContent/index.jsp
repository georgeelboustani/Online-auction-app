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
    <link href="${pageContext.request.contextPath}/css/custom/index_auction.css" rel="stylesheet" media="screen">
   		
    <title>RollBack: Auction Page</title>
</head>
<body>
	
	<div class="container">
		<!-- Navbar - from navbar.jsp -->
		<jsp:include page="navbar.jsp"/>
		
		<!-- content holder for left auctions -->
  		<div class="col-md-9">
  		
  			<!-- nav bar between 10 auctions -->
  			<div class="row auction-nav-bar">
  				<div class="col-md-8">
					<form action="controller" method="get">
						<p>
						<input type='hidden' name='action' value='searchAuction' />
 						<input type="text" name="searchString" class="form-control search-input" placeholder="Search"/>
 						<button  type="submit" class="btn btn-warning">
  							<span class="ui-icon ui-icon-search"></span>
	  					</button>
	  					</p>
  					</form>
  				</div>
  				<div class="col-md-4 next-prev-nav">
  						
  					<!-- 
  					<form class="next-prev-nav-item">
	  					<select class="form-control sort-select">
						  <option selected>Sort</option>
						  <option>Bid: Low-High</option>
						  <option>Bid: High-Low</option>
						  <option>A-Z</option>
						  <option>Z-A</option>
						  <option>Time Left</option>
						</select>&nbsp;
	  					<button type="button" class="btn btn-warning">
	  						<span class="ui-icon ui-icon ui-icon-circle-triangle-w"></span>
	  					</button>
	  					<button type="button" class="btn btn-warning">
	  						<span class="ui-icon ui-icon ui-icon-circle-triangle-e"></span>
	  					</button>
	  				</form>
	  				 -->
  				</div>
  			</div>
  			
  			<!-- auction content -->
	  		<div class="list-group">
	           	<c:choose>
					<c:when test="${searchBean.displayError}">
						${searchBean.errorMessage}
					</c:when>
					
					<c:otherwise>
						<c:set var="count" value="0" scope="page" />
			           	<c:forEach var="auction" items="${searchBean.auctions}" >	
				            <a href="#" class="list-group-item row">
				            	<div class="col-md-3 list-group-item-text">
				            		<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
				            	</div>
				            	<div class="col-md-4 list-group-item-text auction-desc-box">
				            		<span class="auction-item-header">${auction.auctionTitle}</span>
				            		<div class="auction-item-desc">
				            			<span class="text">${auction.auctionDescription}</span>
				            		</div>
				            	</div>
				            	<div class="col-md-2 list-group-item-text">
				            		<p>Starting Price:<br/><strong>${auction.biddingStartPrice}</strong></p>
				            		<p>Current Bid:<br/><strong>${searchBean.bids[count]}</strong></p>
				            		<p>Time Left:<br/><strong>${searchBean.times[count]} mins</strong></p>
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
					            		<!-- jstl valid suggestion to first bid -->
					            		<c:set var="suggestedBid" value="0.0"/>
					            		<c:choose>
					            			<c:when test="${ (searchBean.bids[count]) < (auction.biddingStartPrice) || (searchBean.bids[count] == 0.0) }">  
								        		<c:set var="suggestedBid" value="${auction.biddingStartPrice}"/>
								        	</c:when>
								        	<c:otherwise>
								        		<c:set var="suggestedBid" value="${(searchBean.bids[count] + auction.biddingIncrement)}"/>
								        	</c:otherwise>
								        </c:choose>
								             
										          
				            			<div id="auction-bid" class="input-prepend auction-input">
										  	<span class="add-on">$</span>
										  	<input class="span2 form-control bid-input" id="bid_input_${auction.aid}" type="text" value="${suggestedBid}">
										  	<button id="auction-bid-button" type="button" class="btn btn-sm btn-primary" onclick="doBid(${auction.aid}, ${searchBean.bids[count]}, ${auction.biddingIncrement}); return false;">Bid</button>
										</div>
				            		</p>
				            		<p>
				            			<div class="input-prepend auction-input">
											<button type="button" id="auction-details-button" class="btn btn-sm btn-primary auction-details-button" onclick="getDetails(${auction.aid}); return false;">Show Details</button>
										</div>
				            		</p>
								</div>
								<div id="bidAlert_${auction.aid}"></div>
				            </a>
			            	<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div id="content-dialog"><div id="content-dialog-text"></div></div>
        	<!-- nav bar between 10 auctions -->
  			<div class="row auction-nav-bar">
  				<div class="col-md-9"></div>
  				<div class="col-md-3">
  				<!-- 
  					<p class="next-prev-nav-item">
	  					<button type="button" class="btn btn-warning">
	  						<span class="ui-icon ui-icon ui-icon-circle-triangle-w"></span>
	  					</button>
	  					<button type="button" class="btn btn-warning">
	  						<span class="ui-icon ui-icon ui-icon-circle-triangle-e"></span>
	  					</button>
	  				</p>
	  			-->
  				</div>
  			</div>
  			
  		</div><!-- end list grouping placeholder -->
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
	<script src="${pageContext.request.contextPath}/js/custom/index.js"></script>
</body>
</html>
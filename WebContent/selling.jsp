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
    <link href="${pageContext.request.contextPath}/css/custom/selling.css" rel="stylesheet" media="screen">
   		
    <title>RollBack: My Stuff for Sale</title>
</head>
<body>
	<div class="container">
		<!-- Navbar -->
		<jsp:include page="navbar.jsp"/>
		
		<!-- content placeholder for auctions -->
  		<div class="col-md-9">
	  		<div class="well">
	  			<h2 class="form-selling-heading">Something to Sell?</h2>
	  			<form id="sellingForm" method="GET" action="">		
					<div class="col-md-6">
						<p>
							<span style="display:inline; font-size:15px;">Title:</span>
							<input id="formTitle" name="formTitle" type="text" class="form-control form-selling-input" placeholder="e.g. Its a Boat!" autofocus />
						</p>
						<p>	
							<span style="display:inline; font-size:15px;">Category:</span>
							<select id="formCategory" name="formCategory" class="form-control form-selling-input form-selling-with-title" display:inline;">
								<option value="">Select</option>
								<option value="Furniture">Furniture</option>
								<option value="Fashion">Fashion</option>
								<option value="Books">Books</option>
							</select>
						</p>
						<p>	
							<span style="display:inline; font-size:15px;">Image:</span>
							<input id="formImage" name="formImage" type="file" onchange="handleFiles(this.files)" class="form-control form-selling-input" placeholder="Upload Image" autofocus />
						</p>
						
						<div class="col-md-6"><span style="display:inline; font-size:15px;">Starting Price:</span></div>
						<div class="col-md-6"><input id="formStartPrice" name="formStartPrice" type="text" class="form-control form-selling-input form-selling-with-price" placeholder="0.00" autofocus /></div>
						
						<div class="col-md-6"><span style="display:inline; font-size:15px;">Reserve Price:</span></div>
						<div class="col-md-6"><input id="formReservePrice" name="formReservePrice" type="text" class="form-control form-selling-input form-selling-with-price" placeholder="0.00" autofocus /></div>
					</div>
					
					<div class="col-md-6">
						<p>
							<span style="display:inline; font-size:15px;">Item Description:</span>
							<textarea id="formItemDescription" name="formItemDescription" class="form-control form-selling-input" placeholder="e.g. A boat with wings!" autofocus></textarea>
						</p>
						<p>
							<span style="display:inline; font-size:15px;">Postage Description:</span>
							<textarea id="formPostageDescription" name="formPostageDescription" class="form-control form-selling-input" placeholder="e.g. The boat will be shipped on a boat" autofocus></textarea>
						</p>
						<div class="col-md-6"><span style="display:inline; font-size:15px;">Duration:</span><br/><span style="font-size:10px">(min:3mins max:60mins):</span></div>
						<div class="col-md-6"><input id="formDuration" name="formDuration" type="text" class="form-control form-selling-input" autofocus value="10"/></div>
						<div class="col-md-6"><span style="display:inline; font-size:15px;">Min Bid Increment:</span><br/><span style="font-size:10px">(min 0.10)</span></div>
						<div class="col-md-6"><input id="formMinBid" name="formMinBid" type="text" class="form-control form-selling-input form-selling-with-price" placeholder="0.10" autofocus /></div>
					</div>
		  			<button id="formSubmitItem" type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
		  			<div id="formSellingAlert"></div>
	  			</form>
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
	<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/additional-methods.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
	
	<!-- custom scripts -->
	<script src="${pageContext.request.contextPath}/js/custom/selling.js"></script>
</body>
</html>
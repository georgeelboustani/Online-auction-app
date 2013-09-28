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
	            
	            <a href="#" class="list-group-item row">
	            	<div class="col-md-3 list-group-item-text">
	            		<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
	            	</div>
	            	<div class="col-md-4 list-group-item-text">
	            		<h4>Title Title Title</h4>
	            		<p>Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
	            	</div>
	            	<div class="col-md-2 list-group-item-text">
	            		<p>Current Bid:<br/><strong>$3.50</strong></p>
	            		<p>Time Left:<br/><strong>00d 00h 00min</strong></p>
	            	</div>
	            	<div class="col-md-3 list-group-item-text">
	            		<p>
	            			<div class="input-prepend auction-input">
							  <span class="add-on">$</span>
							  <input class="span2 form-control bid-input" id="appendedPrependedInput" type="text">
							  <button type="button" class="btn btn-sm btn-primary">Bid</button>
							</div>
		            		
	            		</p>
					</div>
	            </a>
	            
	            <a href="#" class="list-group-item row">
	            	<div class="col-md-3 list-group-item-text">
	            		<img data-src="holder.js/140x140" src="${pageContext.request.contextPath}/img/cat.jpg" class="img-circle" alt="140x140" style="width: 140px; height: 140px;">
	            	</div>
	            	<div class="col-md-4 list-group-item-text">
	            		<h4>Title Title Title</h4>
	            		<p>Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
	            	</div>
	            	<div class="col-md-2 list-group-item-text">
	            		<p>Current Bid:<br/><strong>$3.50</strong></p>
	            		<p>Time Left:<br/><strong>00d 00h 00min</strong></p>
	            	</div>
	            	<div class="col-md-3 list-group-item-text">
	            		<p>
	            			<div class="input-prepend auction-input">
							  <span class="add-on">$</span>
							  <input class="span2 form-control bid-input" id="appendedPrependedInput" type="text">
							  <button type="button" class="btn btn-sm btn-primary">Bid</button>
							</div>
		            		
	            		</p>
					</div>
	            </a>

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
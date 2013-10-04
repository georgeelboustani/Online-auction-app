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
   		
    <title>RollBack: Admin Page</title>
</head>
<body>
	
	<div class="container">
		<!-- Navbar - from navbar.jsp -->
		<jsp:include page="navbar.jsp"/>
		
		
			<div class="col-md-6 well">
				<form action="controller" method="get">
					<input type='hidden' name='action' value='banUser' />
					<p>
						<span style="font-size:18px; font-weight:bold;">Ban User ID: </span>
						<input class="form-control" type='text' name='ban_uid' placeholder="e.g. 5" style="width: 40%;" />
					</p>
					<p>
						<span style="font-size:18px; font-weight:bold;">Reasons: </span>
						<textarea class="form-control" name="reasons" style="width:50%;"></textarea>
					</p>
					<p>
						<input class="btn btn-sm btn-primary" type="submit" name="submit" value="Ban User"/>
					</p>
				</form>
			</div>
			<div class="col-md-6 well">
				<form action="controller" method="get">
					<input type='hidden' name='action' value='haltAuction' />
					<p>
						<span style="font-size:18px; font-weight:bold;">Halt Auction ID: </span>
						<input class="form-control" type='text' name='auctionId' placeholder="e.g. 15" style="width: 40%;" />
					</p>
					<p>
						<span style="font-size:18px; font-weight:bold;">Reasons: </span>
						<textarea class="form-control" name="reasons" style="width:50%;"></textarea>
					</p>
					<p>
						<input class="btn btn-sm btn-primary" type="submit" name="submit" value="Halt Auction"/>
					</p>
				</form>
			</div>
		</div>
      	
	</div><!-- /end container -->
	
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed JS script files at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-switch.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
	
</body>
</html>
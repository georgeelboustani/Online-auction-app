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
          	<a href="${pageContext.request.contextPath}/index.jsp" class="dropdown-toggle" data-toggle="dropdown">Auctions <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/index.jsp">All Auctions</a></li>
            <li><a href="controller?action=viewMyBid">My Bids</a></li>
            <li><a href="controller?action=viewSelling">Selling</a></li>
            <li class="divider"></li>
            <li class="dropdown-header"></li>
            <li><a href="#">Purchase History</a></li>
            </ul>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">User Profile</a></li>
              <li><a href="#">Change Password</a></li>
            </ul>
          </li>
          <li><a href="${pageContext.request.contextPath}/login.jsp?action=logout">Logout</a></li>
        </ul>
      </div>
      
      
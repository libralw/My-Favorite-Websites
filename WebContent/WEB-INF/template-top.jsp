<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="pragma" content="no-cache">
  <title>My Favorite Websites</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
 
  <style>
  #nav {
  	border: 1px solid #C0C0C0;
  	border-radius: 10px;
  	width: 300px;
  	padding-top: 10px;
  	padding-bottom: 50px;
  }
  
  #main {
  	padding-left: 100px;
  }
  
  #newpassword {
  	padding-left: 120px;
  }
  
  </style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
</head>

<body>
<%@ page import="databeans.UserBean" %>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-center">
		        <font size="7">My Favorite Websites</font>
			</h3>
		</div>
	</div>
</div>
<div class="container">
	<hr/>
</div>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-4 column" id="nav">

		<c:set var="user" value="${user}" scope="session"/>
		<c:choose>
			<c:when test="${(empty user)}">
				<p style="font-size: 22px; font-weight: bold; color: #428bca">Welcome!</p>
				<ul class="nav nav-stacked nav-pills">
					<li>
						<a href="login.do">Login</a>
					</li>	
					<li>
						<a href="register.do">Register</a>
					</li>
					<li>
						<a href="login.do?email=public">Login in as a visitor!</a>
					</li>
					<li><br/></li>
				</ul>
			</c:when>
			<c:when test="${(user.lastName == 'visitor')}">
				<p style="font-size: 22px; font-weight: bold; color: #428bca">Welcome!</p>
				<ul class="nav nav-stacked nav-pills">
					<li>
						<a href="login.do">Login</a>
					</li>	
					<li>
						<a href="register.do">Register</a>
					</li>
					<li><br/></li>
				</ul>
				<p style="font-weight: bold">Favorites from:</p>
				
				<c:forEach var="u" items="${userList}">
				<ul class="nav nav-stacked nav-pills">	
					<li>
						<a href="list.do?email=${u.email}">
							${u.firstName} ${u.lastName}
						</a>
					</li>
				</ul>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p style="font-weight: bold">Hi, ${user.firstName} ${user.lastName}</p>
				<ul class="nav nav-stacked nav-pills">
					<li>
						<a href="manage.do">Manage your favorites</a>
					</li>
					<li>
						<a href="change-pwd.do">Change Password</a>
					</li>
					<li>
						<a href="logout.do">Logout</a>
					</li>
					<li><br/></li>
				</ul>
				<p style="font-weight: bold">Favorites from:</p>
				<c:forEach var="u" items="${userList}">
				<ul class="nav nav-stacked nav-pills">	
					<li>
						<a href="list.do?email=${u.email}">
							${u.firstName} ${u.lastName}
						</a>
					</li>
				</ul>
				</c:forEach>
			</c:otherwise>
		</c:choose>
			
		</div>
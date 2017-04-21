<!DOCTYPE html>
<%@ page import= "data.Client" %>
<html lang="en" >
<head>
	<meta charset="UTF-8">
	<title>fortune - gamify investing</title>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="mdstyle.css">
	<%Client local = new Client();
		request.getSession().setAttribute("client", local);%>
</head>
<script>
	function validate() {
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET",
				"${pageContext.request.contextPath}/LoginServlet?username="
						+ document.getElementById("username").value
						+ "&password="
						+ document.getElementById("password").value,
				false);
		xhttp.send();
		if (xhttp.responseText.trim().length > 0) {
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}
		return true;
	};
</script>
<body ng-app="InvestCS" ng-controller="mainPageController" ng-cloak class="grad">
	
	<md-toolbar class="siteToolbar">
		<div class="md-toolbar-tools">
			<h2 align="center" flex md-truncate>fortune - gamify investing</h2>			
		</div>
		
	</md-toolbar>

	<h1>Log in to your account</h1>

	<form name="myform" method="GET" action="mainPage.jsp"
		onsubmit="return validate();">
		<div id="formerror"></div>
	    <div>Username</div>
			<input type="text" id="username" name="username"/>
	    <br>
	    <div>Password</div>
	    <input type="text" id="password" name="password"/>
	    <br>
		<input type="submit" value="Log In"/>
  </form>
  <br>
  <div>
	<a href="signup.jsp" id="createAccount">
		<input type="button" value="Create New Account">
	</a>
	</div>
	<div>
	<a href="guest.jsp">
  			<button>Login as Guest</button>
	</a>
	</div>

<!-- Angular Material requires Angular.js Libraries -->
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

	<!-- Angular Material Library -->
	<script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>

	<!-- Your application bootstrap  -->
	<script type="text/javascript" src="app.js"></script>
</body>
</html>
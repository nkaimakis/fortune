<!DOCTYPE html>
<html lang="en" >
<head>
	<meta charset="UTF-8">
	<title>fortune - gamify investing</title>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="mdstyle.css">

</head>
<script>
	function validate() {
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET",
				"${pageContext.request.contextPath}/CreateUserServlet?firstname="
						+ document.getElementById("firstname").value
						+ "&lastname="
						+ document.getElementById("lastname").value
						+ "&username="
						+ document.getElementById("username").value
						+ "&password="
						+ document.getElementById("password").value
						+ "&email=" + document.getElementById("email").value,
				false);
		xhttp.send();
		if (xhttp.responseText.trim().length > 0) {
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}
		return true;
	};
</script>
<body ng-app="InvestCS" ng-cloak ng-controller="mainPageController">
	
	<md-toolbar class="siteToolbar">
		<div class="md-toolbar-tools">
			<h2 align="center" flex md-truncate>fortune - gamify investing</h2>			
		</div>
		
	</md-toolbar>

	<h1>Create your new account!</h1>

	<form name="myform" method="GET" action="mainPage.jsp"
		onsubmit="return validate();">
		<div id="formerror"></div>
		<div>First Name</div>
		<input type="text" id="firstname" placeholder="First Name" /> <br>
		<div>Last Name</div>
		<input type="text" id="lastname" placeholder="Last Name" />
		<div>Email</div>
		<input type="text" id="email" placeholder="Email" /> <br>
		<div>Username</div>
		<input type="text" id="username" placeholder="Username" /> <br>
		<div>Password</div>
		<input type="text" id="password" placeholder="Password" />
		<br>
		<button type="submit" id="createAccount">Submit</button>
	</form>
	
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
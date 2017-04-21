<!DOCTYPE html>
<%@ page import="data.Client" %>
<%@ page import="data.User" %>
<html lang="en" >
<head>
	<meta charset="UTF-8">
	<title>fortune - gamify investing</title>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="mdstyle.css">
</head>
<!-- <<<<<<< HEAD>>>>>>> cca088bc82a1e4c35490880ec325d7322fa83d84 -->
<% Client local = (Client) request.getSession().getAttribute("client");%>

<% User currentUser = (User) local.getUser();%>
<% String username = currentUser.getUsername();
System.out.println(username);
String password = currentUser.getPassword();
String email = currentUser.getEmail();
String fname = currentUser.getFname();
String lname = currentUser.getLname();
double balance = currentUser.getBalance(); %>
<body ng-app="InvestCS" ng-cloak class="grad">
	<div layout="column" ng-controller="mainPageController" id="grad">
		<md-sidenav md-component-id="left" class="md-sidenav-left">
		<md-list>
			<md-list-item>
				<md-button class="nav-item" href="mainPage.jsp">
					<h3>Main Page</h3>
				</md-button>
			</md-list-item>
			<md-list-item>
				<md-button class="nav-item" href="history.jsp">
					<h3>History</h3>
				</md-button>
			</md-list-item>
			<md-list-item>
				<md-button class="nav-item" href="profile.jsp">
					<h3>Profile</h3>
				</md-button>
			</md-list-item>
		</md-list>
		</md-sidenav>
		<md-toolbar class="siteToolbar">
			<div class="md-toolbar-tools">
				<md-button class="siteButton" ng-click="openLeftMenu()">
					<md-icon ng-bind="'menu'"></md-icon>
				</md-button>
				<h2 align="center" flex md-truncate>fortune - gamify investing</h2>
				<md-button class="siteButton" href="profile.jsp">
					<md-icon ng-bind="'person'"></md-icon>
					Profile
				</md-button>
				<md-button class="siteButton" href="login.jsp">
					<md-icon ng-bind="'exit_to_app'"></md-icon>
					Logout
				</md-button>
				
			</div>
			
		</md-toolbar>
		<md-content>
			<div class="grad" layout="column">
				<div layout="column">
					<h1>User Profile Details</h1>
					<table class="userDetails" ng-controller="mainPageController">
					<script type="text/javascript">
						updateUserData = function($scope){
							var firstname = document.getElementById("firstName").value;
							var lastname = document.getElementById("lastName").value;
							var email = document.getElementById("email").value;
							var password = document.getElementById("password").value;
							var xhttp = new XMLHttpRequest();
							xhttp.open("GET",
									"${pageContext.request.contextPath}/ProfileUpdateServlet?firstname=" +
											firstname + 
											"&lastname=" + lastname + 
											"&email=" + email + 
											"&password=" + password,
									false);
							xhttp.send();
							return true;
							
					};
						
					</script>
						<tr>
							<td>
								<h3>First Name:</h3>
							</td>
							<td>
								<h3><%=local.user.getFname() %></h3>
							</td>
							<td>
								<input type="text" id="firstName" ng-model="userData.firstName" name="firstName">
							</td>
						</tr>
						<tr>
							<td>
								<h3>Last Name:</h3>
							</td>
							<td>
								<h3><%=local.user.getLname() %></h3>
							</td>
							<td>
								<input type="text" id="lastName" ng-model="userData.lastName" name="lastName">
							</td>
						</tr>
						<tr>
							<td>
								<h3>Email:</h3>
							</td>
							<td>
								<h3><%=local.user.getEmail() %></h3>
							</td>
							<td>
								<input type="text" id="email" ng-model="userData.email" name="email">
							</td>
						</tr>
						<tr>
							<td>
								<h3>Password:</h3>
							</td>
							<td>
								<h3><%=local.user.getPassword() %></h3>
							</td>
							<td>
								<input type="text" id="password" name="password">
							</td>
						</tr>
						<tr>
							<td>
								<h3>Username:</h3>
							</td>
							<td>
								<h3><%=local.user.getUsername() %></h3>
							</td>
							<td>
								<p ng-model="userData.username" name="balance">{{userData.username}}</p>
							</td>
						</tr>
						<tr>
							<td>
								<h3>Balance:</h3>
							</td>
							<td>
								<h3>$<%=local.user.getBalance() %></h3>
							</td>
							<td>
								<p ng-model="userData.balance" name="balance">{{userData.balance}}</p>
							</td>
						</tr>
						<tr>
							<td>
								<form action="profile.jsp" method="GET" onsubmit="return updateUserData();">
									<input type="submit" value="Update Info" name="username">
								</form>								
							</td>
						</tr>
					</table>
				</div>
			</div>
		</md-content>
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
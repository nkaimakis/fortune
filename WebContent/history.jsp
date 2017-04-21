<!DOCTYPE html>
<html lang="en" >
<%@ page import= "data.Client" %>
<%@ page import= "data.User" %>
<%@ page import= "data.Stock" %>
<%@ page import= "data.Transaction" %>
<%@ page import= "java.util.List" %>
<head>
	<meta charset="UTF-8">
	<title>fortune - gamify investing</title>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="mdstyle.css">

</head>
<script>
function loadHistory(){
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",
			"${pageContext.request.contextPath}/TransactionHistoryServlet?",
			false);
	xhttp.send();
	var transactionData = JSON.parse(xhttp.responseText);
	var table = document.getElementById("stocksOwned");
	for (var i=0; i<transactionData.length; i++)
	{
	  var transaction = JSON.parse(transactionData[i]);
	  var row = document.createElement("TR");
	  var tU= document.createElement("TD");
	  var tT= document.createElement("TD");
	  var tA= document.createElement("TD");
	  var tP= document.createElement("TD");
	  var tTi= document.createElement("TD");
	  tU.appendChild(document.createTextNode(transaction.username));
	  tT.appendChild(document.createTextNode(transaction.ticker));
	  tA.appendChild(document.createTextNode(transaction.amount));
	  tP.appendChild(document.createTextNode(transaction.price));
	  tTi.appendChild(document.createTextNode(transaction.time));
	  row.appendChild(tU);
	  row.appendChild(tT);
	  row.appendChild(tA);
	  row.appendChild(tP);
	  row.appendChild(tTi);
	  table.appendChild(row);
	}
/* 
	for (var i=0; i<transactionData.length; i++)
	{
	  var transaction = JSON.parse(transactionData[i]);
	  console.log(transaction);
	  var text = document.createElement("span");
	  var textNode1 = document.createTextNode(transaction.username+"    ");
	  var textNode2 = document.createTextNode(transaction.ticker+"    ");
	  var textNode3 = document.createTextNode(transaction.amount+"    ");
	  var textNode4 = document.createTextNode(transaction.price+"    ");
	  var textNode5 = document.createTextNode(transaction.time+"    ");
	  text.appendChild(textNode1);
	  text.appendChild(textNode2);
	  text.appendChild(textNode3);
	  text.appendChild(textNode4);
	  text.appendChild(textNode5);

	  var element = document.getElementById("transactionsContainer");
	  element.appendChild(text);
	  
	  
	  /* document.getElementById("transactionUsername"+transaction.time).innerHTML = transaction.username;
	  document.getElementById("transactionTicker"+transaction.time).innerHTML = transaction.ticker;
	  document.getElementById("transactionAmount"+transaction.time).innerHTML = transaction.amount;
	  document.getElementById("transactionPrice"+transaction.time).innerHTML = transaction.price;
	  document.getElementById("transactionTime"+transaction.time).innerHTML = transaction.time; */
	return true;
	
	
};

setInterval(function loadHistory(){
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",
			"${pageContext.request.contextPath}/TransactionHistoryServlet?",
			false);
	xhttp.send();
	var transactionData = JSON.parse(xhttp.responseText);
	var table = document.getElementById("stocksOwned");
	for (var i=0; i<transactionData.length; i++)
	{
	  var transaction = JSON.parse(transactionData[i]);
	  var row = document.createElement("TR");
	  var tU= document.createElement("TD");
	  var tT= document.createElement("TD");
	  var tA= document.createElement("TD");
	  var tP= document.createElement("TD");
	  var tTi= document.createElement("TD");
	  tU.appendChild(document.createTextNode(transaction.username));
	  tT.appendChild(document.createTextNode(transaction.ticker));
	  tA.appendChild(document.createTextNode(transaction.amount));
	  tP.appendChild(document.createTextNode(transaction.price));
	  tTi.appendChild(document.createTextNode(transaction.time));
	  row.appendChild(tU);
	  row.appendChild(tT);
	  row.appendChild(tA);
	  row.appendChild(tP);
	  row.appendChild(tTi);
	  table.appendChild(row);
	}
	return true;
	
	
}, 60000);
</script>
<body onload="loadHistory()" ng-app="InvestCS" ng-cloak class="grad">
	<div layout="column" ng-controller="mainPageController" class="grad">
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
				<h2 align="center" flex md-truncate>fortune - Past Activity and Transaction History</h2>
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
		<md-content class="grad">
			<div class="historyContent" layout="column">
				<div layout="column">
					<h1 class="historyTitle">Recent Stock Activity</h1>
					<md-card id="transactionsContainer">
						<table class="stocksOwned" id = "stocksOwned">
							<tr>
								<td class="dataField">User</td>
								<td class="dataField">Stock Name</td>
								<td class="dataField">Stock Amount</td>
								<td class="dataField">Stock Price</td>
								<td class="dataField">Time</td>
							</tr>
							<%-- <%
					List<Transaction> transactions = (List<Transaction>)request.getSession().getAttribute("history");
					 for (int i=0; i < transactions.size(); i++) {
					 %> --%>
							<!-- <tr> -->
							<%-- 	<td class="dataField" id="transactionUsername<%=transactions.get(i).getTime()%>">
									{{transaction.username}}
								</td>
								<td class="dataField" id="transactionTicker<%=transactions.get(i).getTime()%>">
									{{transaction.ticker}}
								</td>
								<td class="dataField" id="transactionAmount<%=transactions.get(i).getTime()%>">
									{{transaction.amount}}
								</td>
								<td class="dataField" id="transactionPrice<%=transactions.get(i).getTime()%>">
									{{transaction.price}}
								</td>
								<td class="dataField" id="transactionTime<%=transactions.get(i).getTime()%>">
									{{transaction.time}}
								</td>
							</tr>
							<%} %> --%>
						</table>
					</md-card>
					
				</md-button>
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
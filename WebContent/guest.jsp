<!DOCTYPE html>
<html lang="en" >
<%@ page import= "data.Client" %>
<%@ page import= "data.User" %>
<%@ page import= "data.Stock" %>
<%@ page import= "java.util.List" %>
<head>
	<meta charset="UTF-8">
	<title>fortune</title>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="mdstyle.css">

</head>
<%Client local = (Client)request.getSession().getAttribute("client"); %>
<script>
function loadStocks(){
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",
			"${pageContext.request.contextPath}/StockUpdateServlet?",
			false);
	xhttp.send();
	var stockData = JSON.parse(xhttp.responseText);
	console.log(stockData);
	for (var i=0; i<stockData.length; i++)
	{
		
	  var stock = JSON.parse(stockData[i]);
	  console.log(stock);
	  document.getElementById("stockSymbol"+i).innerHTML = stock.symbol;
	  document.getElementById("stockName"+i).innerHTML = stock.name;
	  document.getElementById("stockPrice"+i).innerHTML = stock.price;
	  document.getElementById("stockChange"+i).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("daylow"+i).innerHTML = stock.daylow;
	  document.getElementById("dayhigh"+i).innerHTML = stock.dayhigh;
	  document.getElementById("week52low"+i).innerHTML = stock.week52low;
	  document.getElementById("week52high"+i).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+i).innerHTML = stock.movingav50day;
	  document.getElementById("image"+i).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;
	
};

setInterval(function loadStocks(){
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",
			"${pageContext.request.contextPath}/StockUpdateServlet?",
			false);
	xhttp.send();
	var stockData = JSON.parse(xhttp.responseText);

	for (var i=0; i<stockData.length; i++)
	{
	  var stock = JSON.parse(stockData[i]);
	  document.getElementById("stockSymbol"+i).innerHTML = stock.symbol;
	  document.getElementById("stockName"+i).innerHTML = stock.name;
	  document.getElementById("stockPrice"+i).innerHTML = stock.price;
	  document.getElementById("stockChange"+i).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("daylow"+i).innerHTML = stock.daylow;
	  document.getElementById("dayhigh"+i).innerHTML = stock.dayhigh;
	  document.getElementById("week52low"+i).innerHTML = stock.week52low;
	  document.getElementById("week52high"+i).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+i).innerHTML = stock.movingav50day;
	  document.getElementById("image"+i).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;
	
}, 60000);
</script>


<body onload="loadStocks()" style="font-family: garamond;" id="grad"   ng-app="InvestCS" ng-controller="mainPageController" ng-cloak>
	<div layout="column">
		<md-toolbar class="siteToolbar">
			<div class="md-toolbar-tools">
				<md-button class="siteButton" ng-click="openLeftMenu()">
					<md-icon ng-bind="'menu'"></md-icon>
				</md-button>
				<h2 align="center" flex md-truncate>fortune - gamify investment</h2>
				<md-button class="siteButton" href="login.jsp">
					<md-icon ng-bind="'exit_to_app'"></md-icon>
					Logout
				</md-button>
				
			</div>
			
		</md-toolbar>
		<md-content layout="row" flex>
			<div flex id="activitySummary" layout="column">
				<div class="stocksNotFollowing">
					<h2 class="center">Other Stocks</h2>
					<%
					 for (int i=0; i < 19; i++) {
					 %>
					<md-card layout="row" flex>
			        	<md-card-title>
			          		<md-card-title-text layout="column" flex>
<%-- 			            		<h3>Stock: <span id="stockSymbol<%=i%>"></span>
		          				</h3>
		          				<span flex></span>
		          				<h3>Value: <span id="stockPrice<%=i%>"></span></h3>
		          				<span flex></span>
		          				<h3>
		          				Change: <span id="stockChange<%=i%>"></span>%</h3> --%>
		          				<h3>Stock: <span id="stockSymbol<%=i%>"></span> (<span id="stockName<%=i%>"></span>)
		          				</h3>
		          				<span flex></span>
		          				<h4>Value: $<span id="stockPrice<%=i%>"></span>     Change: <span id="stockChange<%=i%>"></span>%</h4>
		          				<h4>Day Low: $<span id="daylow<%=i%>"></span>		Day High: $<span id="dayhigh<%=i%>"></span></h4>
		          				<h4>Year Low: $<span id="week52low<%=i%>"></span>		Year High: $<span id="week52high<%=i%>"></span></h4>
			          			<h4>50 Day Moving Average: $<span id="movingav50day<%=i%>"></span></h4>
			          			<span flex><img id="image<%=i%>" src="" width="350"></span>
		          				
		          				
			          		</md-card-title-text>
			        	</md-card-title>
			      	</md-card>
			      	 <%
					 }
					 %>
				</div>
<%-- 				<div class="usersFollowing" ng-if="<%=!local.isGuest()%>">
					<h2 class="center" >Users You Follow</h2>
					<md-card ng-repeat="user in users" layout="row" flex>
			        	<md-card-title>
			          		<md-card-title-text>
			            		<h1 class="">{{user.firstName}} {{user.lastName}}</h1>
			            		<h3 class="">Earnings to Date: {{user.portfolioPerformance}}</h3>
			          		</md-card-title-text>
			        	</md-card-title>
			        	<md-card-actions layout="column" layout-align="center">
			          		<md-button class="siteButton" onclick="showPopup()">Invest in User</md-button>
			          		<md-button class="siteButton" onclick="showPopup()">Sell Investment</md-button>
			        	</md-card-actions>
			      	</md-card>
				</div> --%>
				
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
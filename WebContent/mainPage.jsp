<!DOCTYPE html>
<html lang="en" >
<%@ page import= "data.Client" %>
<%@ page import= "data.User" %>
<%@ page import= "data.Stock" %>
<%@ page import= "java.util.List" %>
<head>
	<meta charset="UTF-8">
	<title>fortune - gamify investing</title>
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
	<%if (local.isGuest()) {%>
	var stockData = JSON.parse(xhttp.responseText);

	for (var i=0; i<stockData.length; i++)
	{
	  var stock = JSON.parse(stockData[i]);
	  document.getElementById("stockSymbol"+stock.symbol).innerHTML = stock.symbol;
	  document.getElementById("stockName"+stock.symbol).innerHTML = stock.name;
	  document.getElementById("stockPrice"+stock.symbol).innerHTML = stock.price;
	  document.getElementById("stockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("dayhigh"+stock.symbol).innerHTML = stock.daylow;
	  document.getElementById("daylow"+stock.symbol).innerHTML = stock.dayhigh;
	  document.getElementById("week52high"+stock.symbol).innerHTML = stock.week52low;
	  document.getElementById("week52low"+stock.symbol).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+stock.symbol).innerHTML = stock.movingav50day;
	  document.getElementById("image"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;
	
	<%} else {%>
	var stockData = JSON.parse(xhttp.responseText);
	var ownedStocks = stockData[0];
	var allStocks = stockData[1];
	
 	for (var i=0; i<ownedStocks.owned.length; i++)
	{
		  var stock = JSON.parse(ownedStocks.owned[i]);
		  document.getElementById("ownedStockSymbol"+stock.symbol).innerHTML = stock.symbol;
		  document.getElementById("ownedStockName"+stock.symbol).innerHTML = stock.name;
		  document.getElementById("ownedStockPrice"+stock.symbol).innerHTML = stock.price;
		  document.getElementById("ownedStockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
		  document.getElementById("quantity"+stock.symbol).innerHTML = stock.amountOwned;
		  document.getElementById("ownedDayHigh"+stock.symbol).innerHTML = stock.daylow;
		  document.getElementById("ownedDayLow"+stock.symbol).innerHTML = stock.dayhigh;
		  document.getElementById("ownedWeek52low"+stock.symbol).innerHTML = stock.week52low;
		  document.getElementById("ownedWeek52high"+stock.symbol).innerHTML = stock.week52high;
		  document.getElementById("ownedMovingAv50day"+stock.symbol).innerHTML = stock.movingav50day;
		  document.getElementById("ownedImage"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on";
	}  
	
	for (var i=0; i<allStocks.all.length; i++)
	{
	  var stock = JSON.parse(allStocks.all[i]);
	  document.getElementById("stockSymbol"+stock.symbol).innerHTML = stock.symbol;
	  document.getElementById("stockName"+stock.symbol).innerHTML = stock.name;
	  document.getElementById("stockPrice"+stock.symbol).innerHTML = stock.price;
	  document.getElementById("stockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("dayhigh"+stock.symbol).innerHTML = stock.daylow;
	  document.getElementById("daylow"+stock.symbol).innerHTML = stock.dayhigh;
	  document.getElementById("week52low"+stock.symbol).innerHTML = stock.week52low;
	  document.getElementById("week52high"+stock.symbol).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+stock.symbol).innerHTML = stock.movingav50day;
	  document.getElementById("image"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;

	
	<%}%>
};

setInterval(function loadStocks(){
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",
			"${pageContext.request.contextPath}/StockUpdateServlet?",
			false);
	xhttp.send();
	<%if (local.isGuest()) {%>
	var stockData = JSON.parse(xhttp.responseText);

	for (var i=0; i<stockData.length; i++)
	{
	  var stock = JSON.parse(stockData[i]);
	  document.getElementById("stockSymbol"+stock.symbol).innerHTML = stock.symbol;
	  document.getElementById("stockName"+stock.symbol).innerHTML = stock.name;
	  document.getElementById("stockPrice"+stock.symbol).innerHTML = stock.price;
	  document.getElementById("stockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("dayhigh"+stock.symbol).innerHTML = stock.daylow;
	  document.getElementById("daylow"+stock.symbol).innerHTML = stock.dayhigh;
	  document.getElementById("week52high"+stock.symbol).innerHTML = stock.week52low;
	  document.getElementById("week52low"+stock.symbol).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+stock.symbol).innerHTML = stock.movingav50day;
	  document.getElementById("image"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;
	
	<%} else {%>
	var stockData = JSON.parse(xhttp.responseText);
	var ownedStocks = stockData[0];
	var allStocks = stockData[1];
	
 	for (var i=0; i<ownedStocks.owned.length; i++)
	{
 		  var stock = JSON.parse(ownedStocks.owned[i]);
 		  document.getElementById("ownedStockSymbol"+stock.symbol).innerHTML = stock.symbol;
 		  document.getElementById("ownedStockName"+stock.symbol).innerHTML = stock.name;
 		  document.getElementById("ownedStockPrice"+stock.symbol).innerHTML = stock.price;
 		  document.getElementById("ownedStockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
 		  document.getElementById("quantity"+stock.symbol).innerHTML = stock.amountOwned;
 		  document.getElementById("ownedDayHigh"+stock.symbol).innerHTML = stock.daylow;
 		  document.getElementById("ownedDayLow"+stock.symbol).innerHTML = stock.dayhigh;
 		  document.getElementById("ownedWeek52high"+stock.symbol).innerHTML = stock.week52low;
 		  document.getElementById("ownedWeek52low"+stock.symbol).innerHTML = stock.week52high;
 		  document.getElementById("ownedMovingAv50day"+stock.symbol).innerHTML = stock.movingav50day;
 		  document.getElementById("ownedImage"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on";
 	} 
	
	for (var i=0; i<allStocks.all.length; i++)
	{
	  var stock = JSON.parse(allStocks.all[i]);
	  document.getElementById("stockSymbol"+stock.symbol).innerHTML = stock.symbol;
	  document.getElementById("stockName"+stock.symbol).innerHTML = stock.name;
	  document.getElementById("stockPrice"+stock.symbol).innerHTML = stock.price;
	  document.getElementById("stockChange"+stock.symbol).innerHTML = stock.growth.toFixed(2);
	  document.getElementById("dayhigh"+stock.symbol).innerHTML = stock.daylow;
	  document.getElementById("daylow"+stock.symbol).innerHTML = stock.dayhigh;
	  document.getElementById("week52high"+stock.symbol).innerHTML = stock.week52low;
	  document.getElementById("week52low"+stock.symbol).innerHTML = stock.week52high;
	  document.getElementById("movingav50day"+stock.symbol).innerHTML = stock.movingav50day;
	  document.getElementById("image"+stock.symbol).src = "https://chart.finance.yahoo.com/z?s="+stock.symbol+"&t=1d&q=l&l=on"; 
	}
	return true;
	<%}%>
}, 60000);
</script>
<script>
showPopup = function(stockID, isBuy) {
	var stockTicker = String(stockID);
	console.log(stockTicker); 
	console.log(String(stockID));
    var quantity = prompt("Please enter amount", "0");
    console.log(quantity);
    if (quantity > 0) {
    	var xhttp = new XMLHttpRequest();
    	xhttp.open("GET",
    			"${pageContext.request.contextPath}/TransactionServlet?amount="
    					+ quantity + 
    					"&ticker=" + String(stockTicker),
    			false);
    	xhttp.send();
    	setTimeout(location.reload(), 1500);
    }
}
</script>
<body onload="loadStocks()" style="font-family: garamond;" id="grad"   ng-app="InvestCS" ng-controller="mainPageController" ng-cloak>
	<div layout="column">
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
		<md-content layout="row">
			<div flex="33" id="profileSummary" layout="column">
				<div id="formerror"></div>
				<h2 class="center">Profile Summary</h2>
				<md-card id="profileCard">
					<img id="profilePicture" src="http://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png" width="200px">
					<div layout="column">
						<h2 class="profileInfo"><%=local.user.getFname()+ " " +local.user.getLname()%></h2>
						<h4 class="profileInfo"><%=local.user.getEmail() %></h4>
						<h4 class="profileInfo">$<%=local.user.getBalance() %></h4>	
					</div>
				</md-card>
				
				
			</div>
			<div flex="66" id="activitySummary" layout="column">
				<div class="stocksFollowing">
					
					<h2 class="center" ng-if="<%=!local.isGuest()%>">Stocks You Own</h2>
					<%
					List<Stock> owned = (List<Stock>)request.getSession().getAttribute("owned");
					 for (int i=0; i < owned.size(); i++) {
					 %>
			      	</md-card>
					 <md-card layout="row" flex ng-if="<%=!local.isGuest()%>">
			        	<md-card-title>
			          		<md-card-title-text layout="column">
			  
			            		<h3>Stock: <span id="ownedStockSymbol<%=owned.get(i).getSymbol()%>"></span> (<span id="ownedStockName<%=owned.get(i).getSymbol()%>"></span>)
		          				</h3>
		          				<span flex></span>
		          				<h4>Value: $<span id="ownedStockPrice<%=owned.get(i).getSymbol()%>"></span>     Change: <span id="ownedStockChange<%=owned.get(i).getSymbol()%>"></span>%</h4>
		          				<span flex></span>
		          				<span flex></span> 
		          				<h4>Owned: <span id="quantity<%=owned.get(i).getSymbol()%>"></span></h4>
		          				<h4>Day Low: $<span id="ownedDayLow<%=owned.get(i).getSymbol()%>"></span>		Day High: $<span id="ownedDayHigh<%=owned.get(i).getSymbol()%>"></span></h4>
		          				<h4>Year Low: $<span id="ownedWeek52low<%=owned.get(i).getSymbol()%>"></span>		Year High: $<span id="ownedWeek52high<%=owned.get(i).getSymbol()%>"></span></h4>
			          			<h4>50 Day Moving Average: $<span id="ownedMovingAv50day<%=owned.get(i).getSymbol()%>"></span></h4>
			          			<span flex><img id="ownedImage<%=owned.get(i).getSymbol()%>" src="" width="350"></span>
			          		</md-card-title-text>
			        	</md-card-title>
			        	<md-card-actions layout="column" layout-align="top">
			        		<div class="actionRow" layout="row">
			        			<md-button class="siteButton" id="BUY<%=owned.get(i).getSymbol()%>" ng-if="<%=!local.isGuest()%>" onclick="showPopup(this.id, true)">Buy</md-button>
			        		</div>
			        		<div class="actionRow" layout="row">
			        			<md-button class="siteButton" id="SELL<%=owned.get(i).getSymbol()%>" ng-if="<%=!local.isGuest()%>" onclick="showPopup(this.id, false)">Sell</md-button>
			        		</div>
			          		
			          		
			        	</md-card-actions>
			      	</md-card>
					 <%
					 }
					 %>
					 
				</div>
				<div class="stocksNotFollowing">
					<h2 class="center">Other Stocks</h2>
					<%
					List<Stock> other = (List<Stock>)request.getSession().getAttribute("other");
					 for (int i=0; i < other.size(); i++) {
					 %>
					<md-card layout="row" flex>
			        	<md-card-title>
			          		<md-card-title-text layout="column">
<%-- 			            		<h3>Stock: <span id="stockSymbol<%=i%>"></span>
		          				</h3>
		          				<span flex></span>
		          				<h3>Value: <span id="stockPrice<%=i%>"></span></h3>
		          				<span flex></span>
		          				<h3>
		          				Change: <span id="stockChange<%=i%>"></span>%</h3> --%>
		          				<h3>Stock: <span id="stockSymbol<%=other.get(i).getSymbol()%>"></span> (<span id="stockName<%=other.get(i).getSymbol()%>"></span>)
		          				</h3>
		          				<span flex></span>
		          				<h4>Value: $<span id="stockPrice<%=other.get(i).getSymbol()%>"></span>     Change: <span id="stockChange<%=other.get(i).getSymbol()%>"></span>%</h4>
		          				<h4>Day Low: $<span id="daylow<%=other.get(i).getSymbol()%>"></span>		Day High: $<span id="dayhigh<%=other.get(i).getSymbol()%>"></span></h4>
		          				<h4>Year Low: $<span id="week52low<%=other.get(i).getSymbol()%>"></span>		Year High: $<span id="week52high<%=other.get(i).getSymbol()%>"></span></h4>
			          			<h4>50 Day Moving Average: $<span id="movingav50day<%=other.get(i).getSymbol()%>"></span></h4>
			          			<span flex><img id="image<%=other.get(i).getSymbol()%>" src="" width="350"></span>
		          				
		          				
			          		</md-card-title-text>
			        	</md-card-title>
			        	<md-card-actions layout="column" layout-align="top">
			        		<div class="actionRow" layout="row">
			        			<md-button class="siteButton" id="BUY<%=other.get(i).getSymbol()%>" ng-if="<%=!local.isGuest()%>" onclick="showPopup(this.id, true)">Buy</md-button>
			        		</div>
			        		<div class="actionRow" layout="row">
			        			<md-button class="siteButton" id="SELL<%=other.get(i).getSymbol()%>" ng-if="<%=!local.isGuest()%>" onclick="showPopup(this.id, false)">Sell</md-button>
			        		</div>
			          		
			          		
			        	</md-card-actions>
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
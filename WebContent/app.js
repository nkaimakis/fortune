/*
	* You must include the dependency on 'ngMaterial' 
*/
var app = angular.module('InvestCS', ['ngMaterial']);
	app.controller('mainPageController', function($scope, $mdSidenav, $http) {
		$scope.openLeftMenu = function() {
			$mdSidenav('left').toggle();
		};
		
		$scope.userData = [{
				firstName: 'Tommy',
				lastName: 'Trojan',
				username: 'tommytrojan',
				email: 'tommytrojan@usc.edu',
				balance: '$1000000',
		}];
		$scope.leaderboardData = [];
		
		loadUserData = function() {
			$http({
				method: "GET",
				url: "${pageContext.request.contextPath}/GetUserServlet?"
			}).then(function onSuccess(response){
				$scope.userData = response.data;
			}, function onError(response){
				$scope.userData = response.statusText;
			});
		}
		
		loadLeaderboardData = function() {
			$http({
				method: "GET",
				url: "${pageContext.request.contextPath}/GetLeaderboardServlet?"
			}).then(function onSuccess(response){
				$scope.leaderboardData = response.data;
			}, function onError(reponse){
				$scope.leaderboardData = response.statusText;
			});
		}
		
		$scope.guestUser = {
				firstName: 'Tommy',
				lastName: 'Trojan',
				username: 'tommytrojan',
				email: 'tommytrojan@usc.edu',
				balance: '$1000000',
		}
		

		$scope.stockData = [
			{
				stockName: 'AMZN',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'FB',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'MSFT',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'INTC',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'TSLA',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'NFLX',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'SNAP',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'YELP',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'GOOG',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'AAPL',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'NKE',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'PYPL',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'CSCO',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'SPY',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'BAC',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'T',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'GE',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'VXX',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'T',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'
			},

			{

				stockName: 'EEM',
				stockPrice: '$100',
				stockChange: '+1.5%',
				stockQuantity: '0'			
			},
		];

		$scope.stockTransactions = [
			{
				username: 'jessicafu',
				ticker: 'APPL',
				amount: '50',
				price: '$75',
				time: '2017-04-15T21:38:29+00:00',
				sell: 'true',
				success: 'true',
			},
			{
				username: 'tommytrojan',
				ticker: 'GOOG',
				amount: '70',
				price: '$100',
				time: '2017-04-15T21:38:29+00:00',
				sell: 'true',
				success: 'true',
			},
			{
				username: 'billybob',
				ticker: 'FB',
				amount: '35',
				price: '$40',
				time: '2017-04-15T21:38:29+00:00',
				sell: 'true',
				success: 'true',
			},
		];

	});
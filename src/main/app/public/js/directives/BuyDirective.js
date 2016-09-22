angular.module('ecommerce-lite').directive('buy', 
	[function() {

		return {
			strict: 'E',
			scope: {
				buys: '=buyRef'
			},
			templateUrl: 'partials/buy.html',
		};
}]);
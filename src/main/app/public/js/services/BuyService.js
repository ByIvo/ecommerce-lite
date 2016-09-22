angular.module('ecommerce-lite').factory('Buy',
	['$resource', function($resource) {
		return $resource('buys/:id');
	}]);
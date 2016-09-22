angular.module('ecommerce-lite').factory('Buy',
	['$resource', function($resource) {
		return $resource('http://localhost:8084/ecommerce-lite/buys/:id');
	}]);
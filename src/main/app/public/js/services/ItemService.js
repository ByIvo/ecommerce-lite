angular.module('ecommerce-lite').factory('Item',
	['$resource', function($resource) {
		return $resource('items/:id', null, {
			'update': {method: 'PUT'}
		});
	}]);
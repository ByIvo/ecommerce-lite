angular.module('ecommerce-lite').factory('Item',
	['$resource', function($resource) {
		return $resource('items/:id', null, {
			'update': {method: 'PUT'},
			'sellable': {method: 'GET', params: {id:'sellable'}, isArray: true}
		});
	}]);
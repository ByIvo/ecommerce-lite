angular.module("ecommerce-lite", ['ngRoute', 'ngResource', 'ui.utils.masks', 'angularUtils.directives.dirPagination'])
.config(['$routeProvider', '$resourceProvider', function($routeProvider, $resourceProvider) {

	$routeProvider.when("/items",
	{
		templateUrl: 'partials/items.html',
		controller: 'ItemsController'
	});

	$routeProvider.when("/items/new",
	{
		templateUrl: 'partials/form-item.html',
		controller: 'ItemController'
	});

	$routeProvider.when("/items/:itemId/edit",
	{
		templateUrl: 'partials/form-item.html',
		controller: 'ItemController'
	});

	$routeProvider.otherwise({
		redirectTo: '/items'
	});
}]);
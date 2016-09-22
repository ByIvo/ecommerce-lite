angular.module("ecommerce-lite", ['ngRoute', 'ngResource', 'ui.utils.masks', 'angularUtils.directives.dirPagination'])
.config(['$routeProvider', '$resourceProvider', function($routeProvider, $resourceProvider) {

	$routeProvider.when("/items",
	{
		templateUrl: 'partials/items.html',
		controller: 'ItemsController'
	})
	.when("/items/new",
	{
		templateUrl: 'partials/form-item.html',
		controller: 'ItemController'
	})
	.when("/items/:itemId/edit",
	{
		templateUrl: 'partials/form-item.html',
		controller: 'ItemController'
	})

	.when("/buy/new",
	{
		templateUrl: 'partials/new-buy.html',
		controller: 'BuyController'
	})
	.otherwise({
		redirectTo: '/items'
	});
}]);
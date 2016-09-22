angular.module('ecommerce-lite').directive('cart', ['Cart', function(Cart) {

	function link(scope, element, attrs) {
		scope.buy = Cart.buy;
	}

	return {
		templateUrl: 'partials/cart.html',
		strict: 'E',
		scope: {},
		link: link
	};
}]);
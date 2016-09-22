angular.module('ecommerce-lite').directive('cartItem', function() {

	return {
		templateUrl: 'partials/cart-item.html',
		strict: 'E',
		scope: {
			cartItem: '=cartItemRef',
			buy: '='
		}
	};
});
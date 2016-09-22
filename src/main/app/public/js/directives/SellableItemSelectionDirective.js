angular.module('ecommerce-lite').directive('sellableItemSelectable',['Cart', function(Cart) {
	
	function link(scope, element, attrs) {
		scope.quantity = 1;

		scope.addItem = function() {
			Cart.buy().addItem(scope.sellableItem, scope.quantity);
		};
	}

	return {
		strict: 'E',
		scope: {
			'sellableItem': '='
		},
		templateUrl: 'partials/sellable-item-selection.tpl.html',
		link: link
	};
}]);
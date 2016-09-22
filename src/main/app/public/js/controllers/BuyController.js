angular.module('ecommerce-lite').controller('BuyController',
	['$scope', 'Item', 'Cart', 'Buy', '$location', function($scope, Item, Cart, Buy, $location) {
		$scope.sellableItems = [];
		$scope.sellableItemsFilter = '';
		$scope.buy = Cart.buy();

		$scope.finishBuy = function() {
			var promise = $scope.buy.$save();

			promise
			.then(function() {
				Cart.clearBuy();
				$scope.$emit('success', "Compra feita com sucesso!");
				$location.path("#/buys");
			})
			.catch(function(error) {
				$scope.$emit('error', error.data);
			});
		};

		findSellableItems();

		function findSellableItems() {
			var promise = Item.sellable().$promise;

			promise.then(function(data) {
				$scope.sellableItems = data;
			})
			.catch(function(error) {
				$scope.$emit('error', 'Não foi possível buscar os itens disponíveis para venda!');
			});
		}
	}]);
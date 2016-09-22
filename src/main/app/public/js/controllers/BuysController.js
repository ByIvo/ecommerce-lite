angular.module('ecommerce-lite').controller('BuysController', 
	['$scope', 'Buy', function($scope, Buy) {

		queryBuys();

		function queryBuys() {
			var promise = Buy.query().$promise;

			promise.then(function(data) {
				$scope.buys = data;
			})
			.catch(function(error) {
				$scope.$emit('error', "Não foi possível buscar as compras realizadas." + error.data);
			});
		}
	}]);
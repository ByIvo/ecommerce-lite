angular.module('ecommerce-lite').controller('ItemController',
	['$scope', 'Item', '$routeParams', '$location', function($scope, Item, $routeParams, $location) {

		$scope.userMessage = '';
		$scope.idEditing = !!$routeParams.itemId;

		if($scope.idEditing) {

			Item.get({id: $routeParams.itemId}, function(item) {
				$scope.item = item;
			}, function(error) {
				$scope.$emit('error', "Não foi possível encontrar o item desejado");
				$location.path("#/items/");
			});

		}else $scope.item = new Item();

		$scope.save = function() {
			$scope.modelError={};

			var promise = $scope.item.$save();
			
			saveAction(promise, 'Item cadastrado com sucesso');	
		};

		$scope.edit = function() {
			var promise = Item.update({"id": $routeParams.itemId}, $scope.item).$promise;
			
			saveAction(promise, 'Item alterado com sucesso');
		};

		function saveAction(promise, message) {
			promise.then(function() {
				$scope.$emit('success', message);
				$location.path("#/items/");
			})
			.catch(function(error) {
				$scope.$emit('warning', "Preencha corretamente os campos!");
				$scope.modelError=error.data;
			});
		}
	}]);
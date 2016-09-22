angular.module('ecommerce-lite').controller('ItemsController',
	['$scope', 'Item', '$location', function($scope, Item, $location) {
		$scope.items = [];
		$scope.orderField = 'name';
		$scope.filterValue = '';
		$scope.desc = false;
		$scope.showedItems = [];
		$scope.itemsPerPage = 5;
		$scope.userMessage = '';
		
		$scope.orderByField = function(field) {
			$scope.orderField = field;
			$scope.desc = !$scope.desc;
		};

		$scope.editItem = function(item) {
			$location.path("/items/"+item.id+"/edit");
		};


		$scope.deleteItem = function(item, $event) {
			$scope.$emit('confirm', 'Deseja deletar esse item?', 'Esse processo não poderá ser desfeito', function() {
				confirmDeletation(item);
			});

			//STOP PROPAGATION; OTHERWISE EDIT METHOD ALSO WILL BE CALLED;
			return $event.stopPropagation();
		};

		var confirmDeletation = function(item) {
			var promise = Item.delete({"id": item.id}).$promise;
		
			promise
			.then(function() {
				$scope.$emit('success', 'Item deletado com sucesso');
				findItems();
			})
			.catch(function(error) {
				$scope.$emit('error', message.statusText);
			});
		};

		var findItems = function() {
			var promise = Item.query().$promise;

			promise.then(function(data){
				$scope.items = data;
			})
			.catch(function(error) {
				$scope.$emit('error', "Não foi possível buscar os items");
			});
		};

		findItems();
	}]);
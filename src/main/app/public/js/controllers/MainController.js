angular.module('ecommerce-lite').controller('MainController',
	['$scope', '$timeout', function($scope, $timeout) {

		function resetMessage(delay) {
			$timeout(function() {
				$scope.showMessage=false;
				$scope.message="";
				$scope.messageType="";
			}, delay);
		}

		function showMessage(title, type) {
			$scope.showMessage= true;
			$scope.message= title;
			$scope.messageType=type;

			resetMessage(5000);
		}

		resetMessage(0);

		$scope.$on('success', function(event, title) {
			showMessage(title, 'success');
		});

		$scope.$on('error', function(event, title) {
			showMessage(title, 'danger');
		});

		$scope.$on('warning', function(event, title) {
			showMessage(title, 'warning');
		});

		$scope.$on('confirm', function(event, title, message, action) {
			swal({
				title: title,
				text: message,
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#FF5252",
				confirmButtonText: 'Continuar',
				cancelButtonText: "Cancelar",
				closeOnConfirm: true
			},action);
		});
	}]);
angular.module('ecommerce-lite').factory('CartItem',
	[function() {
		
		function newCartItem(item) {
			var cartItem = {};

			cartItem.itemQnt = 0;
			cartItem.item = item;

			cartItem.getTotalCost = function(buy) {
				 var totalPrice = this.item.boughtPrice + buy.getAvgExpenses();
        		 var totalProfit = totalPrice * buy.profitRate;
        		return this.itemQnt * (totalPrice + totalProfit);
			};

			return cartItem;
		}

		return {
			newCartItem: newCartItem
		};
	}]);
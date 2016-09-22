angular.module('ecommerce-lite').factory('Cart',
	['CartItem', 'Buy', function(CartItem, Buy) {
		
		function newBuy() {
			var buy = new Buy();

			buy.boughtItems = [];
			buy.totalExpenses =  400.0;
			buy.profitRate = 0.0;

			buy.addItem = function(item, quantity) {
				var cartItem = this.findItemBuy(item);
				cartItem.itemQnt+=quantity;
			};

			buy.removeItem = function(item) {
				var index = _.indexOf(buy.boughtItems, item);

				if(index > -1) {
					buy.boughtItems.splice(index, 1);
				}
			};

			buy.findItemBuy = function(item) {
				var cartItem = _.find(buy.boughtItems, function(itemBuy) {
					return item.id === itemBuy.item.id;
				});

				if(!cartItem) {
					cartItem = CartItem.newCartItem(item);

					buy.boughtItems.push(cartItem);
				}

				return cartItem;
			};

			buy.getAvgExpenses = function() {
				var totalItemQnt = this.getTotalItemQnt();
				if(totalItemQnt === 0) return 0;

				return this.totalExpenses / totalItemQnt;
			};

			buy.getTotalCost = function(){
				var totalCost = 0.0;
				var self = this;

				_.each(this.boughtItems, function(cartItem) {
					totalCost += cartItem.getTotalCost(self);
				});

				return totalCost;
			};

			buy.getTotalItemQnt = function(){
				var totalItems = 0;

				_.each(this.boughtItems, function(cartItem) {
					totalItems += cartItem.itemQnt;
				});

				return totalItems;
			};

			return buy;
		}

		var controlBuy = newBuy();

		function clearBuy() {
			controlBuy = newBuy();
		}

		return {
			buy: function() {return controlBuy;},
			clearBuy: clearBuy
		};
	}]);
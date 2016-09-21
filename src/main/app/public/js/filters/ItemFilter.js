angular.module('ecommerce-lite').filter('itemFilter',
	function() {
		return function(items, filter) {
			if(!items) return;

			if(!filter) return items;

			filter = filter.toLowerCase();
			var camposFiltro = Array.prototype.slice.call(arguments, 2);

			return items.filter(function (item) {
				return _.some(camposFiltro, function(itemField) {
					var itemFieldValue = item[itemField] + '';

					itemFieldValue= itemFieldValue.toLowerCase();
					return itemFieldValue.indexOf(filter) > -1;
				});
			});

		};
	});
angular.module('ecommerce-lite').directive('thOrderable', function() {
	return {
		templateUrl: 'partials/th-orderable.tpl.html',
		strict: 'A',
		scope: {
			fieldName: '=',
			visibleName: '='
		},
		link: function(scope, element, attrs) {
			element.on('click', function(){

				var escapedFieldName = scope.$apply(attrs.fieldName);

				scope.$parent.$apply(function() {
					scope.$parent.orderByField(escapedFieldName);
				});
				
			});
		}
	};
});
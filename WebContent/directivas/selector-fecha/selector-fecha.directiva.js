(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.directive('selectorFecha', selectorFecha);
	
	function selectorFecha() {
		return {
			restrict: 'E',
			scope: {
				fechaSeleccionada: '=',
				mes : '='
			},
			templateUrl: './directivas/selector-fecha/selector-fecha.html',
			controller: 'controladorSelectorFecha',
			controllerAs: 'VMSelectorFecha'
		}
	}
})();
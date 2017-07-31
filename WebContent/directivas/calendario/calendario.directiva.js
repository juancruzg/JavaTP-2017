(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.directive('calendario', calendario);
	
	function calendario() {
		return {
			restrict: 'E',
			scope: {
				fecha: '=',
				accion: '=',
				fechaSeleccionada: '='
			},
			templateUrl: './directivas/calendario/calendario.html',
			controller: 'controladorCalendario',
			controllerAs: 'VMCalendario'
		}
	}
})();
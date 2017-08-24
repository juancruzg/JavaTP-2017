(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorSelectorFecha', controladorSelectorFecha);
	
	controladorSelectorFecha.$inject = ['$scope'];
	
	function controladorSelectorFecha($scope) {
		var vm = this;
		
		vm.mes = $scope.mes;
		vm.seleccionarFecha = seleccionarFecha;
		vm.fechaSeleccionada = $scope.fechaSeleccionada || null;
		vm.mostrarCalendario = mostrarCalendario;
		vm.mostrandoCalendario = false;		
		
		if (vm.fechaSeleccionada)
			vm.fechaStr = $scope.fechaSeleccionada.toString();
		
		function seleccionarFecha(dia) {
			vm.fechaStr = dia.fecha.toString();
			$scope.fechaSeleccionada = dia.fecha;
		}
		
		function mostrarCalendario(mostrar) {
			if (mostrar)
				vm.mostrandoCalendario = mostrar;
			else
				vm.mostrandoCalendario = !vm.mostrandoCalendario;
		}
	}
})();
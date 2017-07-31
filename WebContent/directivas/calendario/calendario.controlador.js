(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorCalendario', controladorCalendario);
	
	controladorCalendario.$inject = ['$scope'];
	
	function controladorCalendario($scope) {
		var vm = this;
		
		vm.mesSeleccionado = null;
		vm.semanas = [];
		vm.seleccionarMes = seleccionarMes;
		vm.seleccionarFecha = seleccionarFecha;
		vm.mesSiguiente = mesSiguiente;
		vm.mesAnterior = mesAnterior;
		
		// Mes que se muestra en el calendario
		var mesSeleccionado = $scope.fecha || new Date();
		var fechaSeleccionada = $scope.fechaSeleccionada || null;

		if (fechaSeleccionada)
			fechaSeleccionada.setHours(0, 0, 0, 0);
		
		seleccionarMes(mesSeleccionado);
		
		function mesSiguiente($event) {
			$event.preventDefault();
			mesSeleccionado = mesSeleccionado.add(1, 'm');
			seleccionarMes(mesSeleccionado);
		}
		
		function mesAnterior($event) {
			$event.preventDefault();
			mesSeleccionado = mesSeleccionado.add(-1, 'm');
			seleccionarMes(mesSeleccionado);
		}
		
		function seleccionarFecha(dia) {
			fechaSeleccionada = dia.fecha;
			
			if ($scope.accion)
				$scope.accion(dia);
			
			$scope.fechaSeleccionada = fechaSeleccionada;
			
			seleccionarMes(mesSeleccionado);
		}
		
		function seleccionarMes(fecha) {
			if (!fecha)
				fecha = new Date();
			
			vm.mesSeleccionado = fecha.getMonthName();
			vm.semanas = crearMes(fecha);
		}
		
		function crearMes(fecha) {
			var semanas = [];
			
			var mes = fecha.getMonth();
			
			// Busco el primero de mes.
			fecha = new Date(fecha.getFullYear(), mes, 1);
			
			// Empiezo a retroceder en fechas hasta llegar a el comienzo de semana.
			while (fecha.getDay() != 1) {
				fecha = fecha.add(-1, 'd');
			}
			
			// Lleno el array de semanas con los días hasta que la semana pertenezca a otro mes.
			do {
				semanas.push(crearSemana(fecha, mes));
				fecha = fecha.add(1, 'w');
			} while(fecha.getMonth() === mes);
			
			return semanas;
		}
		
		function crearSemana(fecha, mes) {
			var dias = [];
			
			var today = new Date();
			
			// A ambas fechas a comparar le seteo la hora 0 para poder compararlas exactamente
			fecha.setHours(0, 0, 0, 0);			
			today.setHours(0, 0, 0, 0);
			
			for (var i = 0; i < 7; i++) {
				dias.push({
					seleccionado: fechaSeleccionada && (fecha.getTime() === fechaSeleccionada.getTime()),
					numero: fecha.getDate(),
					enMesActual: fecha.getMonth() === mes,
					esHoy: fecha.getTime() === today.getTime(),
					fecha: fecha
				});
				
				fecha = fecha.add(1, 'd');
			}
			
			return dias;
		}
	}
})();
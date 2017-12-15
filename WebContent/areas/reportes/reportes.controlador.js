(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorReportes', controladorReportes);
	
	controladorReportes.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$util"];
	
	function controladorReportes ($scope, $state, $api, $tabla, $q, $util) {
		var vm = this;
		vm.listar = listar;
		vm.mostrarEditar = mostrarEditar;
		vm.control = {};
		
		function listar(paginaActual, porPagina) {
			var deferred = $q.defer();
			var data = { 
				  'paginaActual': paginaActual, 
				  'porPagina': porPagina,
				  'mostrarInactivos': vm.mostrarInactivos
			  };
			 $api.getData("Ventas", data).then(function(data) {
				  // Armo los th de la tabla y se lo paso junto con la data a la promesa
				 var headers = [
					  { "caption": "Numero", "isVisible": true, "dataField": "id" },
					  { "caption": "Fecha", "isVisible": true, "dataField": "fecha" },
					  { "caption": "Nombre", "isVisible": true, "dataField": "cliente.nombre" },
					  { "caption": "Apellido", "isVisible": true, "dataField": "cliente.apellido" }
				  ];
				  deferred.resolve($tabla.popularTabla(data, headers));
			 });
			 return deferred.promise;
		}
		
		function mostrarEditar(venta){
			$state.go("reporte.venta", { "detalle" : venta.detalles });
		}
		//$state.go('reporte.venta', { "idCliente": undefined, "venta": undefined });
	}
})();
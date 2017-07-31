(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorNuevaVenta', controladorNuevaVenta);
	
	controladorNuevaVenta.$inject = ['$scope', '$api', '$tabla', '$q'];
	
	function controladorNuevaVenta($scope, $api, $tabla, $q) {
		var vm = this;
		
		vm.listarClientes = listarClientes;
		vm.accionClientes = accionClientes;
		vm.listarProductos = listarProductos;
		vm.accionProductos = accionProductos;
		vm.listarProductos = listarProductos;
		vm.accionProductos = accionProductos;
		vm.clienteSeleccionado = null;
		vm.limpiarCliente = limpiarCliente;
		vm.fecha = new Date();
	  
		function limpiarCliente() {
			vm.clienteSeleccionado = null;
		}
		
		function listarProductos(texto) {
			
		}
		
		function accionProductos(producto) {
			
		}
		
		function accionClientes(cliente) {
			vm.clienteSeleccionado = cliente;
		}
	  
		function listarClientes(texto) {
			var deferred = $q.defer();
		  
			var data = {
				'paginaActual': 0,
				'porPagina': 10,
				'query': texto
			};
			
			var output = {};
		  
			$api.getData("Clientes", data).then(function(data) {
				output.data = data;
				output.columnasAMostrar = ["nombre", "apellido"];
				  	
				deferred.resolve(output);
			});
			  		  
			return deferred.promise;
		}
	}
})();
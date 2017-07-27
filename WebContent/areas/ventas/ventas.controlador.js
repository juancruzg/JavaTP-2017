(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorVentas', controladorVentas);
	
	controladorVentas.$inject = ['$scope', '$api', '$tabla'];
	
	function controladorVentas($scope, $api, $tabla) {
		var vm = this;
		
		vm.prueba = prueba;
		vm.pruebaAccion = pruebaAccion;
	  
		function pruebaAccion(row) {
			alert(row.domicilio);
		}
	  
		function prueba(texto) {
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
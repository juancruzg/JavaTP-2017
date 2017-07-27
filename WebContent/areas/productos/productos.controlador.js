(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorProductos', controladorProductos);
	
	controladorProductos.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$util"];
	
	function controladorProductos ($scope, $state, $api, $tabla, $q, $util) {
		var vm = this;
		
		vm.mostrarEditar = mostrarEditar;
		vm.listar = listar;
		
		function listar(paginaActual, porPagina) {
			var deferred = $q.defer();
			  
			  // Parámetros que le paso al server.
			  var data = { 
				  'paginaActual': paginaActual, 
				  'porPagina': porPagina
			  };
			  
			  $api.getData("Productos", data).then(function(data) {
				  // Armo los th de la tabla y se lo paso junto con la data a la promesa
				  var headers = [
					  { "caption": "Descripción", "isVisible": true, "dataField": "nombre" }
				  ];
				  
				  deferred.resolve($tabla.popularTabla(data, headers));
			  });
			  
			  return deferred.promise;
		}
	}
})();
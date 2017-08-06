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
		vm.mostrarInactivos = false;
		vm.control = {};
		
		
		function listar(paginaActual, porPagina) {
			var deferred = $q.defer();
			  
			  // Parámetros que le paso al server.
			  var data = { 
				  'paginaActual': paginaActual, 
				  'porPagina': porPagina,
				  'mostrarInactivos': vm.mostrarInactivos
			  };
			  
			  $api.getData("Productos", data).then(function(data) {
				  // Armo los th de la tabla y se lo paso junto con la data a la promesa
				  var headers = [
					  { "caption": "Descripción", "isVisible": true, "dataField": "descripcion" },
					  { "caption": "Marca", "isVisible": true, "dataField": "marca" }
				  ];
				  
				  deferred.resolve($tabla.popularTabla(data, headers));
			  });
			  
			  return deferred.promise;
		}
		
		  function mostrarEditar(producto) {		  
			  if (producto) {
				  $state.go('productos.editar', { "idProducto": producto.id, "producto": producto });
			  }
			  else
				  $state.go('productos.editar', { "idProducto": null });		  
		  }
	}
})();
(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorProductosEditar', controladorProductosEditar);
	
	controladorProductosEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams", "$util"];
	
	function controladorProductosEditar ($scope, $state, $api, $tabla, $q, $stateParams, $util) {
		  var vm = this;
		  
		  // Ni bien carga el controller, scrolleo hasta el fondo y hago focus al nombre.
		  $util.scrollTo(document.body, document.body.scrollHeight, 500);	
		  $util.focus("descripcion");
		  
		  vm.productoSeleccionado = seleccionarProducto();
		  vm.guardar = guardar;
		  vm.cancelar = cancelar;
		  vm.listaLineas = [];
		  vm.listaTalles = [];
		  vm.listaColores = [];
		  
		  if (vm.productoSeleccionado && vm.productoSeleccionado.lineas)
			  vm.listaLineas = vm.productoSeleccionado.lineas;
		  
		  if (angular.equals({}, vm.productoSeleccionado))
			  vm.productoSeleccionado.activo = true;
		  
		  buscarTalles();
		  buscarColores();
		  
		  function buscarTalles() {
			  $api.getData("Talles").then(function(data) {
				 vm.listaTalles = data; 
			  });
		  }
		  
		  function buscarColores() {
			  $api.getData("Colores").then(function(data) {
				 vm.listaColores = data; 
			  });
		  }
		  
		  function seleccionarProducto() {
			  if (!$stateParams.idProducto)
				  return {};
				  
			  if ($stateParams.producto)
				  return $stateParams.producto;
			  else
				  buscarProducto($stateParams.idProducto);
		  }
		  
		  function buscarProducto(idProducto) {
			  $api.getData("Productos", { "idProducto": idProducto }).then(function(data) {
				  if (data) {
					  vm.productoSeleccionado = data;	

					  if (vm.productoSeleccionado && vm.productoSeleccionado.lineas)
						  vm.listaLineas = vm.productoSeleccionado.lineas;
				  }
				  else
					  vm.productoSeleccionado = null; // En realidad acá debería dar 404...
			  });
		  }
		  
		  function guardar() {
			  
		  }
		  
		  function cancelar() {
			  $state.go('^');
			  $util.scrollTo(document.body, 0, 500);
		  }
	}
})();
	
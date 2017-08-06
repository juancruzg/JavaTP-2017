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
		  // vm.guardar = guardar;
		  vm.cancelar = cancelar;
		  vm.listaLineas = [];
		  
		  if (vm.productoSeleccionado && vm.productoSeleccionado.lineas) {
			  vm.listaLineas = vm.productoSeleccionado.lineas;
		  }
		  
		  if (angular.equals({}, vm.productoSeleccionado))
			  vm.productoSeleccionado.activo = true;
		  
		  function seleccionarProducto() {
			  if (!$stateParams.idProducto)
				  return {};
				  
			  if ($stateParams.producto)
				  return $stateParams.producto;
			  else {
				  var producto = buscarProducto($stateParams.idProducto);
				  
				  if (producto)
					  return producto;
				  else
					  return {};
			  }
		  }
		  
		  function buscarProducto(idProducto) {
			  $api.getData("Productos", { "idProducto": idProducto }).then(function(data) {
				  debugger;
				  if (data) {
					  vm.productoSeleccionado = data;	

					  if (vm.productoSeleccionado && vm.productoSeleccionado.lineas) {
						  vm.listaLineas = vm.productoSeleccionado.lineas;
					  }
				  }
				  else
					  vm.productoSeleccionado = null; // En realidad acá debería dar 404...
			  });
		  }
		  
		  function cancelar() {
			  $state.go('^');
			  $util.scrollTo(document.body, 0, 500);
		  }
	}
})();
	
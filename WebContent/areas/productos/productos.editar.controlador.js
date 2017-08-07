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
		  vm.agregarLinea = agregarLinea;
		  vm.cantidadSeleccionada = 1;
		  vm.listaLineas = [];
		  vm.listaTalles = [];
		  vm.listaColores = [];
		  
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
				  }
				  else
					  vm.productoSeleccionado = null; // En realidad acá debería dar 404...
			  });
		  }
		  
		  function guardar() {
			  var promesa;
			  
			  if (vm.productoSeleccionado && vm.productoSeleccionado.id && vm.productoSeleccionado.id != 0) { 
				  // Si ya existe, lo edito.
				  promesa = $api.postData("Productos", vm.productoSeleccionado);
			  }
			  else {
				  // Si no existe lo creo.
				  promesa = $api.putData("Productos", vm.productoSeleccionado);
			  }
			  
			  promesa.then(function (data) {
				  $state.go('productos', null, { 'reload': true });	  
			  });
		  }
		  
		  function cancelar() {
			  $state.go('^');
			  $util.scrollTo(document.body, 0, 500);
		  }
		  
		  function agregarLinea() {
			  var stock = vm.cantidadSeleccionada;
			  var color = vm.colorSeleccionado;
			  var talle = vm.talleSeleccionado;
			  			  
			  if (color && talle && stock) {
				  if (!vm.productoSeleccionado.lineas)
					  vm.productoSeleccionado.lineas = [];
				  
				  var linea = { 
						  "stock": stock,
						  "color": color,
						  "talle": talle
				  }
	
				  var lineaActual = null;
				  
				  vm.productoSeleccionado.lineas.forEach(function(l) {
					 if (l.color.id === linea.color.id && l.talle.id === linea.talle.id) {
						 lineaActual = l;
						 return;
					 } 
				  });
				  
				  if (!lineaActual)
					  vm.productoSeleccionado.lineas.push(linea);
				  else
					  lineaActual.stock = lineaActual.stock + linea.stock;
			  }
			  
		  }
	}
})();
	
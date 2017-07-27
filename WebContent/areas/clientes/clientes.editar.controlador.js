(function() {
  'use strict';
  
  angular
      .module('shop-management')
      .controller('controladorClientesEditar', controladorClientesEditar);

  controladorClientesEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams", "$util"];

  function controladorClientesEditar($scope, $state, $api, $tabla, $q, $stateParams, $util) {
	  var vm = this;
	  
	  // Ni bien carga el controller, scrolleo hasta el fondo y hago focus al nombre.
	  $util.scrollTo(document.body, document.body.scrollHeight, 500);	
	  $util.focus("nombre");
	  
	  vm.clienteSeleccionado = seleccionarCliente();
	  vm.guardar = guardar;
	  vm.cancelar = cancelar;
	  
	  function guardar() {
		  var promesa;
		  
		  if (vm.clienteSeleccionado && vm.clienteSeleccionado.id && vm.clienteSeleccionado.id != 0) { 
			  // Si ya existe, lo edito.
			  promesa = $api.postData("Clientes", vm.clienteSeleccionado);
		  }
		  else {
			  // Si no existe lo creo.
			  promesa = $api.putData("Clientes", vm.clienteSeleccionado);
		  }
		  
		  promesa.then(function (data) {
			  $state.go('clientes', null, { 'reload': true });	  
		  });
	  };
	  
	  function seleccionarCliente() {
		  if (!$stateParams.idCliente)
			  return {};
			  
		  if ($stateParams.cliente)
			  return $stateParams.cliente;
		  else
			  return buscarCliente($stateParams.idCliente);
	  }
	  
	  function buscarCliente(idCliente) {
		  $api.getData("Clientes", { "idCliente": idCliente }).then(function(data) {
			  if (data)
				  vm.clienteSeleccionado = data;	
			  else
				  vm.clienteSeleccionado = null; // En realidad acá debería dar 404...
		  });
	  }
	  
	  function cancelar() {
		  $state.go('^');
		  $util.scrollTo(document.body, 0, 500);
	  }
  }
})();
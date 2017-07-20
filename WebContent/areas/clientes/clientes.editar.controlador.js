(function() {
  'use strict';
  
  angular
      .module('shop-management')
      .controller('controladorClientesEditar', controladorClientesEditar);

  controladorClientesEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams", "$util"];

  function controladorClientesEditar($scope, $state, $api, $tabla, $q, $stateParams, $util) {
	  var vm = this;
	  
	  // Ni bien carga el controller, scrolleo hasta el fondo.
	  $util.scrollTo(document.body, document.body.scrollHeight, 500);	
	  
	  vm.clienteSeleccionado = seleccionarCliente();
	  vm.guardar = guardar;
	  vm.cancelar = cancelar;
	  
	  function guardar() {
		  if (vm.clienteSeleccionado && vm.clienteSeleccionado.id && vm.clienteSeleccionado.id != 0) { 
			  // Si ya existe, lo edito.
			  $api.postData("Clientes", vm.clienteSeleccionado).then(function(data) {
				  // Vuelvo al padre.
				  $state.go('^', null, { 'reload': true });
				  $util.scrollTo(document.body, 0, 500);		  
			  });
		  }
		  else {
			  // Si no existe lo creo.
			  $api.putData("Clientes", vm.clienteSeleccionado).then(function(data) {
				  // Vuelvo al padre.
				  $state.go('clientes', null, { 'reload': true });
				  $util.scrollTo(document.body, 0, 500);		  
			  });
		  }
	  };
	  
	  function seleccionarCliente() {
		  if ($stateParams.cliente)
			  return $stateParams.cliente;
		  else if ($stateParams.idCliente)
			  return buscarCliente($stateParams.idCliente);
		  else
			  return {};
	  }
	  
	  function buscarCliente(idCliente) {
		  $api.getData("Clientes", { "idCliente": idCliente }).then(function(data) {
			  if (data && data.data)
				  vm.clienteSeleccionado = data.data;	
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
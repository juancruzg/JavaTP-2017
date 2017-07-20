(function() {
  'use strict';
  
  angular
      .module('shop-management')
      .controller('controladorClientesEditar', controladorClientesEditar);

  controladorClientesEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams"];

  function controladorClientesEditar($scope, $state, $api, $tabla, $q, $stateParams) {
	  var vm = this;
	  
	  if ($stateParams.cliente)
		  vm.clienteSeleccionado = $stateParams.cliente;
	  else
		  buscarCliente($stateParams.idCliente);
	  
	  vm.onClickGuardar = onClickGuardar;
	  
	  function onClickGuardar(cliente){
		  $api.postData("Clientes", cliente).then(function(data) {
			
			
		  });
	  };
	  
	  function buscarCliente(idCliente) {
		  $api.getData("Clientes", { "idCliente": idCliente }).then(function(data) {
			  if (data && data.data)
				  vm.clienteSeleccionado = data.data;	
			  else
				  vm.clienteSeleccionado = null; // En realidad acá debería dar 404...
		  });
	  }
  }
})();
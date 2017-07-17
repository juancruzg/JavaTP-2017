(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorClientesEditar', controladorClientesEditar);

  controladorClientesEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams"];

  function controladorClientesEditar($scope, $state, $api, $tabla, $q, $stateParams) {
	  var vm = this;
	  vm.clienteSeleccionado = $stateParams.cliente;
	  
	  vm.onClickGuardar = onClickGuardar;
	  
	  function onClickGuardar(cliente){
		  
		  $api.postData("Clientes", cliente).then(function(data) {
			
			
		  });
	  };
  }
})();
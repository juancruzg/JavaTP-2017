(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorClientes', controladorClientes);

  controladorClientes.$inject = ["$scope", "$state"];

  function controladorClientes($scope, $state) {
	  var vm = this;
	  
	  vm.renderizarEditarcliente = renderizarEditarcliente;
	  vm.data = {"data": [{"nombre": "Leo", "apellido": "Peretti"}], "header": [{ "caption": "Nombre", "dataField": "nombre" }, { "caption": "Apellido", "dataField": "apellido" }]};
	  	  
	  vm.editTabla = function(e){
		  alert(e);
	  };
	  
	  function renderizarEditarcliente(idCliente) {
		  if(!idCliente)
			  idCliente = 0;
		  
		  $state.go("clientes.editar", {"idCliente": idCliente});
	  }
  }
})();
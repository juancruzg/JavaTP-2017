(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorClientes', controladorClientes);

  controladorClientes.$inject = ["$scope", "$state", "$api", "$tabla", "$q"];

  function controladorClientes($scope, $state, $api, $tabla, $q) {
	  var vm = this;
	  
	  vm.renderizarEditarcliente = renderizarEditarcliente;
	  vm.editarTabla = editarTabla;
	  vm.listar = listar;
	  
	  function listar(paginaActual, porPagina) {
		  var deferred = $q.defer();
		  
		  var data = { 'paginaActual': paginaActual, 'porPagina': porPagina };
		  
		  var headers = [{ "caption": "Nombre", "isVisible": true, "dataField": "nombre" }
		  ,{ "caption": "Apellido", "isVisible": true, "dataField": "apellido" }
		  ,{ "caption": "Dirección", "isVisible": true, "dataField": "domicilio" }
		  ,{ "caption": "Telefono", "isVisible": true, "dataField": "telefono" }];
		  
		  $api.getData("Clientes", data).then(function(data) {
			  console.log(data.data);
			  deferred.resolve($tabla.popularTabla(data.data, headers));
		  });
		  
		  return deferred.promise;
	  }
	  
	  function editarTabla(e) {
		  alert(e);
	  }
	  
	  function renderizarEditarcliente(idCliente) {
		  if(!idCliente)
			  idCliente = 0;
		  
		  $state.go("clientes.editar", {"idCliente": idCliente});
	  }
  }
})();
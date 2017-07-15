(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorClientes', controladorClientes);

  controladorClientes.$inject = ["$scope", "$state", "$api", "$tabla", "$q"];

  function controladorClientes($scope, $state, $api, $tabla, $q) {
	  var vm = this;
	  
	  vm.mostrarEditar = mostrarEditar;
	  vm.editarTabla = editarTabla;
	  vm.listar = listar;
	  
	  function listar(paginaActual, porPagina) {
		  var deferred = $q.defer();
		  
		  var data = { 'paginaActual': paginaActual, 'porPagina': porPagina };
		  
		  var headers = [{ "caption": "Nombre", "isVisible": true, "dataField": "nombre" }
		  ,{ "caption": "Apellido", "isVisible": true, "dataField": "apellido" }];
		  
		  $api.getData("Clientes", data).then(function(data) {
			  deferred.resolve($tabla.popularTabla(data.data, headers));
		  });
		  
		  return deferred.promise;
	  }
	  
	  function editarTabla(e) {
		  alert(e);
	  }
	  
	  function mostrarEditar(cliente) {
		  // Acá le estoy mandando el cliente entero, así que podríamos usarlo dirctamente para mostrarlo
		  // o podemos usar el id para hacer un nuevo get... Elijan uds :) -Juan
		  var idCliente = 0;
		  
		  if (cliente && cliente.id)
			  idCliente = cliente.id;
		  
		  $state.go("clientes.editar", {"idCliente": idCliente});
	  }
  }
})();
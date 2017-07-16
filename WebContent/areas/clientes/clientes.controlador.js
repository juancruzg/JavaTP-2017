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
	  vm.listar = listar;
	  
	  function listar(paginaActual, porPagina) {
		  var deferred = $q.defer();
		  
		  // Parámetros que le paso al server.
		  var data = { 
			  'paginaActual': paginaActual, 
			  'porPagina': porPagina
		  };
		  
		  $api.getData("Clientes", data).then(function(data) {
			  // Armo los th de la tabla y se lo paso junto con la data a la promesa
			  var headers = [
				  { "caption": "Nombre", "isVisible": true, "dataField": "nombre" },
				  { "caption": "Apellido", "isVisible": true, "dataField": "apellido" }
			  ];
			  deferred.resolve($tabla.popularTabla(data.data, headers));
		  });
		  
		  return deferred.promise;
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
(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorClientes', controladorClientes);

  controladorClientes.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$util"];

  function controladorClientes($scope, $state, $api, $tabla, $q, $util) {
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
				  { "caption": "Apellido", "isVisible": true, "dataField": "apellido" },
				  { "caption": "Domicilio", "isVisible": true, "dataField": "domicilio" },
				  { "caption": "Telefono", "isVisible": true, "dataField": "telefono" }
			  ];
			  deferred.resolve($tabla.popularTabla(data.data, headers));
		  });
		  
		  return deferred.promise;
	  }
	  
	  function mostrarEditar(cliente) {
		  if (cliente)
			  $state.go('clientes.editar', { "idCliente": cliente.id, "cliente": cliente });
		  else
			  $state.go('clientes.editar', { "idCliente": null });		  
	  }
  }
})();
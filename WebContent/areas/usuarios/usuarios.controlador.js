(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorUsuarios', controladorUsuarios);


  controladorUsuarios.$inject = ["$scope", "$state", "$http", "$q", "$tabla", "$api"];

  function controladorUsuarios($scope, $state, $http, $q, $tabla, $api) {
	  var vm = this;
	  
	  vm.listar = listar;
	  vm.mostrarEditar = mostrarEditar;
	  vm.mostrarInactivos = false;
	  
	  vm.control = {};
	  
	  function listar(paginaActual, porPagina) {
		  var deferred = $q.defer();
		  
		  // Parámetros que le paso al server.
		  var data = { 
			  'paginaActual': paginaActual, 
			  'porPagina': porPagina,
			  'mostrarInactivos': vm.mostrarInactivos
		  };
		  
		  $api.getData("Usuarios", data).then(function(data) {
			  // Armo los th de la tabla y se lo paso junto con la data a la promesa
			  var headers = [
				  { "caption": "Usuario", "isVisible": true, "dataField": "usuario" },
				  { "caption": "Nombre", "isVisible": true, "dataField": "nombre" },
				  { "caption": "Apellido", "isVisible": true, "dataField": "apellido" },
			  ];
			  
			  deferred.resolve($tabla.popularTabla(data, headers));
		  });
		  
		  return deferred.promise;
	  }
	  
	  function mostrarEditar(usuario) {
		  debugger;
		  if (usuario)
			  $state.go('usuarios.editar', { "loginUsuario": usuario.usuario, "usuario": usuario });
		  else
			  $state.go('usuarios.editar', { "idUsuario": null });		
	  }
  }
})();
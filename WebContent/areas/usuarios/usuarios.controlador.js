(function() {
  'use strict';
  
  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorUsuarios', controladorUsuarios);


  controladorUsuarios.$inject = ["$scope", "$state", "$http"];

  function controladorUsuarios($scope, $state, $http) {
	  var vm = this;
	  
	  vm.getUsuarios = getUsuarios;
	  
	  getUsuarios();
	  
	  function getUsuarios() {
		  $http.post("/ServletUsuario", { "accion": "getUsuarios", "paginaActual": "1", "porPagina": "5" })
		  	.then(function(res) {
		  		console.log(res);
		  	}, function(errorRes) {
		
		  });
	  };
  }
})();
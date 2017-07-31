(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorSelector', controladorSelector);
  
  controladorSelector.$inject = ['$scope'];
  
  function controladorSelector($scope) {
	  var vm = this;
	  
	  vm.input = "";
	  vm.actualizarLista = actualizarLista;
	  vm.accion = accion;
	  vm.lista = [];
	  vm.placeholder = $scope.placeholder;
	  vm.claseIcono = $scope.claseIcono;
	  
	  function actualizarLista() {
		  var output = [];
		  
		  // Primero que nada corro el método listar que me mandan los controladores
		  $scope.listar(vm.input).then(function(dataSelector) {
			  // Para cada fila, para cada columna a mostrar,...
			  dataSelector.data.forEach(function(row) {
				  var string = "";
				  
				  // ...concateno el contenido de la columna y lo agrego a mi arreglo auxiliar.
				  dataSelector.columnasAMostrar.forEach(function(index) {
					  if(string === "")
						  string = row[index]; 
					  else
						  string += " " + row[index];
				  });
				  
				  output.push({ "listItem": string, "originalRow": row });
			  });
			  
			  vm.lista = output;
		  });
	  }
	  
	  function accion(row) {
		  vm.input = "";
		  vm.lista = [];
		  
		  $scope.accion(row);
	  }
  }
})();
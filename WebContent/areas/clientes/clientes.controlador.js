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
	  vm.editarTabla = editarTabla;
	  vm.data = {"body": 
		  [{ "apellido": "Peretti", "nombre": "Leo", "direccion": "A la vuelta de la facu", "telefono": "iphone6" }
		  ,{ "apellido": "Giordano", "nombre": "Nicolás", "direccion": "Santiago y Zeballos", "telefono": "12314" }
		  ,{ "direccion": "San Lorenzo 1759", "telefono": "3444539608", "nombre": "Juan", "apellido": "Grasso"}], 
		  
	  "headers": 
		  [{ "caption": "Nombre", "isVisible": true, "dataField": "nombre" }
		  ,{ "caption": "Apellido", "isVisible": true, "dataField": "apellido" }
		  ,{ "caption": "Dirección", "isVisible": true, "dataField": "direccion" }
		  ,{ "caption": "Teléfono", "isVisible": true, "dataField": "telefono" }]};
	  
	  	  
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
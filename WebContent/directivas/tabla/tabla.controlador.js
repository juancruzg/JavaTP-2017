(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorTabla', controladorTabla);


  controladorTabla.$inject = ["$scope", "$rootScope", "hotkeys", "$tabla"];

  function controladorTabla($scope, $rootScope, hotkeys, $tabla) {
    var vm = this;
    
    var totalTabla = $scope.data.totalTabla; // En el json que trae toda la data de la tabla tendría que también venir el tamaño
    
    // Tal vez... seria buena idea poder pasar como parámetro la data de paginación...
    vm.paginaActual = 0;
    vm.data = null;
    vm.mostrarTabla = mostrarTabla;
    vm.avanzarPagina = avanzarPagina;
    vm.volverPagina = volverPagina;
    
    // Lo corro ni bien carga la tabla para que se muestre...
    mostrarTabla($scope.data);
    
    function avanzarPagina() {
    	if (vm.paginaActual < totalTabla)
    	vm.paginaActual ++;
    	
    	//actualizarTabla();
    }
 
    function volverPagina() {
    	if (vm.paginaActual > 0)
    	vm.paginaActual --;
    	
    	//actualizarTabla();
    }
       
    function actualizarTabla(paginaActual, porPagina) {
    	$scope.listar(paginaActual, porPagina).then(function(data) {
    		mostrarTabla($tabla.popularTabla(data, $scope.data.headers));
    	});   	
    }
    
    function mostrarTabla(data) {
    	var dataSrc = data;
    	
    	var rows = [];
    	var headers = [];
    	

    	console.log(dataSrc);
    	
    	// Primero lleno el array de headers
    	dataSrc.headers.forEach(function(header) {
    		if (header.isVisible)
    			headers.push(header.caption);
    	});
    	
    	// Después lleno el array de rows, ordenándolos de la misma manera que se muestran los headers
		dataSrc.body.forEach(function(originalRow) {
			var row = [];
			
			dataSrc.headers.forEach(function(header) {
				if (header.isVisible) {
					if (originalRow[header.dataField])
						row.push({ "row": originalRow[header.dataField] });
				}
			});
			
			// Pusheo al array la row original
			row.push({ "originalRow": originalRow });
			
			// Al final pusheo el Row a mi array de Rows
			rows.push(row);
		});
		
		vm.data = { "headers": headers, "rows": rows };
    }
  }
})();

(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorTabla', controladorTabla);


  controladorTabla.$inject = ["$scope", "$q", "hotkeys", "$tabla"];

  function controladorTabla($scope, $q, hotkeys, $tabla) {
    var vm = this;
    
    // Tal vez... seria buena idea poder pasar como parámetro la data de paginación...
    vm.paginaActual = 0;
    vm.data = null;
    vm.mostrarTabla = mostrarTabla;
    vm.avanzarPagina = avanzarPagina;
    vm.volverPagina = volverPagina;
    vm.porPagina = 10;
    vm.mostrarEditar = mostrarEditar;
    vm.rowSeleccionado = null;

    $scope.control.actualizarTabla = actualizarTabla;
    
    // Lo corro ni bien carga la tabla para que se muestre...
    actualizarTabla(0, 10);
    
    
    hotkeys.bindTo($scope).add({
        combo: '+',
        description: 'Agregar un nuevo registro',
        callback: function() {
            $scope.mostrarEditar(null);
        }
      });
    
    function avanzarPagina() {
    	if (vm.paginaActual < totalTabla)
    	vm.paginaActual ++;
    	
    	actualizarTabla(vm.paginaActual, vm.porPagina);
    }
 
    function volverPagina() {
    	if (vm.paginaActual > 0)
    	vm.paginaActual --;
    	
    	actualizarTabla(vm.paginaActual, vm.porPagina);
    }
       
    function actualizarTabla(paginaActual, porPagina) {
    	$scope.listar(paginaActual, porPagina).then(function(data) {
    		mostrarTabla(data);
    	});   	
    }
    
    function mostrarTabla(data) {
    	var dataSrc = data;
    	
    	var rows = [];
    	var headers = [];
    	
    	// Primero lleno el array de headers
    	dataSrc.headers.forEach(function(header) {
    		if (header.isVisible)
    			headers.push(header.caption);
    	});

    	// Después lleno el array de rows, ordenándolos de la misma manera que se muestran los headers
		dataSrc.body.forEach(function(originalRow) {
			var row = {};
			
			row.data = [];
			
			dataSrc.headers.forEach(function(header) {
				if (header.isVisible) {
					if (originalRow[header.dataField])
						row.data.push(originalRow[header.dataField]);
				}
			});
			
			// Pusheo al array la row original
			row.originalRow = originalRow;
			
			// Al final pusheo el Row a mi array de Rows
			rows.push(row);
		});

		vm.data = { "headers": headers, "rows": rows };
    }
    
    function mostrarEditar(row) {
    	vm.rowSeleccionado = row;
    	
    	$scope.mostrarEditar(row);
    }
  }
})();

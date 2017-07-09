(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorTabla', controladorTabla);


  controladorTabla.$inject = ["$scope", "$rootScope", "hotkeys"];

  function controladorTabla($scope, $rootScope, hotkeys) {
    var vm = this;

    vm.edit = $scope.edit;
    vm.renderizarEditar = $scope.renderizarEditar;
    vm.data = null;
    
    // Lo corro ni bien carga la tabla para que se muestre...
    mostrarTabla();
    
    function mostrarTabla() {
    	var dataSrc = $scope.data;
    	
    	var rows = [];
    	var headers = [];
    	
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

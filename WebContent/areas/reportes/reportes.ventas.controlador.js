(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorReportesVenta', controladorReportesVenta);
	
	controladorReportesVenta.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$util", "$stateParams", "hotkeys"];
	
	function controladorReportesVenta ($scope, $state, $api, $tabla, $q, $util,$stateParams,hotkeys) {
		var vm = this;
		vm.detalle = $stateParams.detalle;
		
		
	}
})();
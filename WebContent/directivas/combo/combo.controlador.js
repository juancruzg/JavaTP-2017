(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorCombo', controladorCombo);
  
  controladorCombo.$inject = ["$scope"];

  function controladorCombo($scope) {
	 var vm = this;
	 
	 vm.show = $scope.show;
  }
})();
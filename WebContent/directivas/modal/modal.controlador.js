(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('controladorModal', controladorModal);
  
  controladorModal.$inject = ["$scope"];

  function controladorModal($scope) {
	  var vm = this;
	  
	  vm.dialogStyle = {};
	  vm.dialogStyle.width = $scope.width + "px";
	  vm.dialogStyle.height = $scope.heigth + "px";
	  
	  vm.hideModal = hideModal;
	  vm.toggleModal = toggleModal;
	  
	  vm.show = $scope.show;
	  
	  function toggleModal() {
		  vm.show = !vm.show;
	  }
	  
	  function hideModal() {
		  vm.show = false;
	  }
  }
})();
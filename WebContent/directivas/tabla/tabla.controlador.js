(function() {
  'use strict';

  // Create module and controller
  angular
      .module('shop-management')
      .controller('tableController', tableController);


  tableController.$inject = ["$scope", "$rootScope", "hotkeys"];

  function tableController($scope, $rootScope, hotkeys) {
    var vm = this;

    vm.currentPage = 0;
    vm.totalPages = 0;
    
    vm.data = $scope.data;
    vm.remove = $scope.remove;
    vm.edit = $scope.edit;
    
    vm.nextPage = nextPage;
    vm.previousPage = previousPage;
    vm.searchTestChanged = searchTestChanged;
    vm.content = null;

    showList(vm.currentPage);

    hotkeys.bindTo($scope).add({
      combo: 'right',
      description: 'Navegar a la derecha en la tabla',
      allowIn: ['INPUT'],
      callback: function() {
        nextPage();
      }
    }).add({
      combo: 'left',
      description: 'Navegar a la izquierda en la tabla',
      allowIn: ['INPUT'],
      callback: function() {
        previousPage();
      }
    }).add({
      combo: '+',
      description: 'Agregar un nuevo registro',
      callback: function() {
        vm.edit();
      }
    });

    function nextPage() {
      if (vm.currentPage < vm.totalPages - 1)
      {
        vm.currentPage++;

        if (!vm.searchText || vm.searchText === "")
          showList(vm.currentPage);
        else
          search(vm.currentPage);
      }
    }

    function previousPage() {
      if (vm.currentPage > 0) {
        vm.currentPage--;

        if (!vm.searchText || vm.searchText === "")
          showList(vm.currentPage);
        else
          search(vm.currentPage);
      }
    }

    function search(currentPage) {
      var searchText = vm.searchText;

      $scope.search(searchText, currentPage, function(content, numOfRows) {
        vm.content = content;
        vm.totalPages = Math.ceil(numOfRows/6);
        $scope.$apply();
      });
    }

    function showList(currentPage) {
      $scope.data.list(currentPage).then(function (content) {
        vm.content = content.list;
        vm.totalPages = Math.ceil(content.numOfRows/6); //numOfRows
        $scope.$apply();
      });
    }

    function searchTestChanged() {
      vm.currentPage = 0;
      search(0);
    }
  }
})();

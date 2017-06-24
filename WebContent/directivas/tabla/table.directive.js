(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('shopTable', function (){
        return {
          restrict: 'E',
          scope: {
        	'data' : '=',
          	'remove': '=',
            'edit': '=',
            'search': '='
          },
          templateUrl: './areas/shared/table.html',
          controller: 'tableController',
          controllerAs: 'tableVm'
        };
      });
})();

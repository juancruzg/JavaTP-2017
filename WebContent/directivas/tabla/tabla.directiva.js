(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('tabla', function (){
        return {
          restrict: 'E',
          scope: {
        	'data' : '=',
          	'remove': '=',
            'edit': '=',
            'search': '=',
            'renderizarEditar' : '='
          },
          templateUrl: './directivas/tabla/tabla.html',
          controller: 'controladorTabla',
          controllerAs: 'tableVm'
        };
      });
})();

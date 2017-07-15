(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('tabla', function (){
        return {
          restrict: 'E',
          scope: {
        	'data': '=',
          	'mostrarABM': '=',
          	'guardar': '=',
          	'listar': '='
          },
          templateUrl: './directivas/tabla/tabla.html',
          controller: 'controladorTabla',
          controllerAs: 'tableVm'
        };
      });
})();

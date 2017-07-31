(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('selector', function (){
        return {
          restrict: 'E',
          scope: {
          	'listar': '=',
          	'accion': '=',
          	'placeholder': '=',
          	'claseIcono': '='
          },
          templateUrl: './directivas/selector/selector.html',
          controller: 'controladorSelector',
          controllerAs: 'VMSelector'
        };
      });
})();

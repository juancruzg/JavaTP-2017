(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('tabla', function (){
        return {
          restrict: 'E',
          scope: {
          	'mostrarEditar': '=',
          	'listar': '=',
          	'control': '='
          },
          templateUrl: './directivas/tabla/tabla.html',
          controller: 'controladorTabla',
          controllerAs: 'VMTabla'
        };
      });
})();

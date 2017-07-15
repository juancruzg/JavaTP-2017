(function() {
  'use strict';

  angular
      .module('shop-management')
      .directive('tabla', function (){
        return {
          restrict: 'E',
          scope: {
          	'mostrarEditar': '=',
          	'guardar': '=',
          	'listar': '='
          },
          templateUrl: './directivas/tabla/tabla.html',
          controller: 'controladorTabla',
          controllerAs: 'tableVm'
        };
      });
})();

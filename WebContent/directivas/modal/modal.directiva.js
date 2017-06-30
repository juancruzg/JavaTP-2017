(function() {
  "use strict";

  angular
      .module("shop-management")
      .directive("modal", function (){
        return {
          restrict: "E",
          scope: {
        	"show" : "=",
        	"width" : "=",
        	"height" : "="
          },
          replace: true,
          transclude: true,
          templateUrl: "./directivas/modal/modal.html",
          controller: "controladorModal",
          controllerAs: "modalVM"
        };
      });
})();
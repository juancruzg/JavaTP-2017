(function() {
  "use strict";

  angular
      .module("shop-management")
      .directive("combo", function (){
        return {
          restrict: "E",
          scope: {
        	"show" : "="
          },
          templateUrl: "./directivas/combo/combo.html",
          controller: "controladorCombo",
          controllerAs: "comboVM"
        };
      });
})();
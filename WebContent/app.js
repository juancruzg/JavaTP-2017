var app = angular.module('shop-management', ['ui.router', 'cfp.hotkeys']);

app.config(function($stateProvider, $urlRouterProvider) {
	var home = {
		name: "home",
		templateUrl: './areas/home/home.html',
		url:"/home"
	}
	
	$urlRouterProvider.otherwise('/home');
	$stateProvider.state(home);
})
.config(function(hotkeysProvider) {
	hotkeysProvider.cheatSheetDescription = "Mostrar/Ocultar este menú de ayuda";
	hotkeysProvider.templateTitle = "Puede usar los siguientes controles para una mejor experiencia:"
});

app.controller('indexController', indexController);

indexController.$inject = ["hotkeys"];

function indexController(hotkeys) {
  var vm = this;

  vm.toggleCheatSheet = hotkeys.toggleCheatSheet;
  vm.menuToggled = false;

  vm.toggleMenu = toggleMenu;
  
  hotkeys.add({
    combo: '-',
    description: 'Expandir menú principal',
    callback: function() {
      toggleMenu();
    }
  });
  
  function toggleMenu() {
    vm.menuToggled = !vm.menuToggled;
  }
}

var app = angular.module('shop-management', ['ui.router', 'cfp.hotkeys']);

app.config(function($stateProvider, $urlRouterProvider) {
	var home = {
		name: "home",
		templateUrl: "./areas/home/home.html",
		url: "/home"
	}
	
	var clientes = {
		name: "clientes",
		templateUrl: "./areas/clientes/clientes.html",
		url: "/clientes",
		controller: "controladorClientes",
		controllerAs: "VMClientes"
	}
	
	var usuarios = {
		name: "usuarios",
		templateUrl: "./areas/usuarios/usuarios.html",
		url: "/usuarios",
		controller: "controladorUsuarios",
		controllerAs: "VMUsuarios"
	}
	
	var editarClientes = {
		name: "clientes.editar",
		templateUrl: "./areas/clientes/clientes.editar.html",
		url: "/editar/:idCliente",
		params: {cliente: null},
		controller: "controladorClientesEditar",
		controllerAs: "VMClientesEditar"
	}
	
	$urlRouterProvider.otherwise('/home');
	
	$stateProvider
		.state(home)
		.state(clientes)
		.state(usuarios)
		.state(editarClientes);
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

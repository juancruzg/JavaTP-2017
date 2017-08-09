/*var app = angular.module('login', ['ui.router']);
app.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/login');
	$stateProvider.state({
			name: "login",
			templateUrl: "./areas/login/login.html",
			url: "/login"
		})
});
app.controller('loginControlador', loginControlador);


var loginControlador = function($state){
	var vm = this;
	vm.log = function() {
	}
};*/
(function() {
	'use strict';
  
	// Create module and controller
	angular
		.module('shop-management')
		.controller('controladorLogin', controladorLogin);

	controladorLogin.$inject = ["$state", "$usuario"];

	function controladorLogin($state, $usuario) {
		var vm = this;
		var usuario = $usuario.getUsuarioConectado();
		
		if (usuario)
			$state.go("home");
	}
})();	
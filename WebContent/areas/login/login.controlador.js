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

	controladorLogin.$inject = ["$state", "$usuario", "$scope", "$rootScope"];

	function controladorLogin($state, $usuario, $scope, $rootScope) {
		var vm = this;
		
		$usuario.isLoggedIn().then(function(isLoggedIn) {
			if (isLoggedIn)
				$state.go("home");
		});
		
		vm.login = login;
					
		function login() {
			$usuario.login(vm.usuario, vm.clave).then(function(usuario) {
				$rootScope.$broadcast("mostrarMenu", true);
				
				$state.go("home");
			});
		}
	}
})();	
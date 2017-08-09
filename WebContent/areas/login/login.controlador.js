var app = angular.module('login', ['ui.router']);
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
		/*$state.go('home');*/
	}
};
  
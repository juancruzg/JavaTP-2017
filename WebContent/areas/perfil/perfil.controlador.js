(function() {
	"use strict";

	angular
		.module("shop-management")
		.controller("controladorPerfil", controladorPerfil);
	
	controladorPerfil.$inject = [ "$usuario", "$api", "$state" ];
	
	function controladorPerfil($usuario, $api, $state) {
		var vm = this;
		
		vm.guardar = guardar;
		
		$usuario.isLoggedIn().then(function(usuario) {
			vm.usuario = usuario;
		});
		
		function guardar() {
			$api.postData("Usuarios", vm.usuario).then(function(data) {
				$state.go('home');
				
				$usuario.reload(vm.usuario.usuario);
			});
		}
	}
})();
(function() {
	"use strict";

	angular
		.module("shop-management")
		.controller("controladorPerfil", controladorPerfil);
	
	controladorPerfil.$inject = [ "$usuario" ];
	
	function controladorPerfil($usuario) {
		var vm = this;
		
		$usuario.isLoggedIn().then(function(usuario) {
			vm.usuario = usuario;
		});
	}
})();
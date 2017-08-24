(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorHome', controladorHome);
	
	controladorHome.$inject = ["$usuario"];
	
	function controladorHome ($usuario) {
		var vm = this;
		
		$usuario.isLoggedIn().then(function(usuario) {
			vm.usuarioLoggeado = usuario;
		});
	}
})();
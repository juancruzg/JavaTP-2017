(function() {
	'use strict';

	angular
		.module('shop-management')
		.service('$usuario', servicioUsuario);
  
	servicioUsuario.$inject = ["$api"];
	  
	function servicioUsuario ($api) {
		var servicio = this;
		
		servicio.login = login;
		servicio.getUsuarioConectado = getUsuarioConectado;
		
		var usuarioConectado = null;
		
		function login(nombreUsuario, password) {
			//var data = {
			//	"usuario": nombreUsuario,
			//	"password": password
			//}
			//$api.getData("Usuarios", data).then(function(data) {...
			
			usuarioConectado = { 
				"nombreUsuario": nombreUsuario,
				"nombre": "Juan",
				"apellido": "Grasso"
			}
		}
		
		function getUsuarioConectado() {
			return usuarioConectado;
		}
	}

})();
	
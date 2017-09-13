(function() {
	'use strict';

	angular
		.module('shop-management')
		.service('$usuario', servicioUsuario);
  
	servicioUsuario.$inject = ["$api", "$q", "$state"];
	  
	function servicioUsuario ($api, $q, $state) {
		var servicio = this;
		
		servicio.login = login;
		servicio.authorize = authorize;
		servicio.isLoggedIn = isLoggedIn;
		servicio.logout = logout;
		servicio.permitir = permitir;
		servicio.reload = reload;
		
		function login(nombreUsuario, password) {
			var deferred = $q.defer();

			// Este bien podría ir en el controlador... Pero ya que tenemos este servicio, usémsolo
			var data = {
				"usuario": nombreUsuario,
				"password": password,
				"accion": "login"
			}
			
			$api.getData("Login", data).then(function(data) {
				deferred.resolve(data);
			});
			
			return deferred.promise;
		}
		
		function reload(nombreUsuario) {
			var deferred = $q.defer();

			// Este bien podría ir en el controlador... Pero ya que tenemos este servicio, usémsolo
			var data = {
				"usuario": nombreUsuario,
				"accion": "reload"
			}
			
			$api.getData("Login", data).then(function(data) {
				deferred.resolve(data);
			});
			
			return deferred.promise;
		}
		
		function logout() {
			// Este bien podría ir en el controlador... Pero ya que tenemos este servicio, usémsolo
			var data = {
				"accion": "logout"
			}
			
			$api.getData("Login", data).then(function(data) {
				$state.go("login");
			});			
		}
		
		function authorize() {		
			var data = {
				"accion": "estaLoggeado"
			}
			
			// Acá habría que, cada vez llamar al API para ver si el user sigue conectado en la variable de sesión
			$api.getData("Login", data).then(function(data) {
				if (!data)
					$state.go("login");
			});			
		}
		
		function isLoggedIn() {
			var data = {
				"accion": "estaLoggeado"
			}
			
			var deferred = $q.defer();
			
			$api.getData("Login", data).then(function(data) {
				deferred.resolve(data);
			});
			
			return deferred.promise;
		}
		
		function permitir(nombreEstado) {
			var deferred = $q.defer();
			
			isLoggedIn().then(function(usuario) {
				if (usuario) {
					var tienePermisos = false;
					
					usuario.tipoUsuario.modulos.forEach(function(modulo) {
						if (modulo.nombre === nombreEstado) {
							tienePermisos = true;
							return;
						}
					});
					
					if (tienePermisos)
						deferred.resolve();
					else
						deferred.reject("error");
				}
				else
					deferred.reject("login");
			});
			
			return deferred.promise;
		}
	}

})();
	
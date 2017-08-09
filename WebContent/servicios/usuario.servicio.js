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
		
		function login(nombreUsuario, password) {
			var deferred = $q.defer();

			// Este bien podría ir en el controlador... Pero ya que tenemos este servicio, usémsolo
			var data = {
				"usuario": nombreUsuario,
				"password": password
			}
			
			$api.getData("Usuarios", data).then(function(data) {
				deferred.resolve(!!data);
			});
			
			return deferred.promise;
		}
		
		function authorize() {			
			// Acá habría que, cada vez llamar al API para ver si el user sigue conectado en la variable de sesión
			$api.getData("Usuarios").then(function(data) {
				if (!data)
					$state.go("login");
			});			
		}
		
		function isLoggedIn() {
			var deferred = $q.defer();
			
			$api.getData("Usuarios").then(function(data) {
				deferred.resolve(!!data);
			});
			
			return deferred.promise;
		}
	}

})();
	
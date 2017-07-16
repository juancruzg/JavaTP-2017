(function() {
	'use strict';

	angular
		.module('shop-management')
		.service('$tabla', servicioTabla);
	  
	function servicioTabla () {
		this.popularTabla = function(data, headers) {
			var error = false;
			
			if (!data) {
				console.log("La data enviada es nula...");
				error = true;
			}			
			
			/*if (data.constructor === [].constructor) {
				console.log("La data enviada no es un JSON Array");
				error = true;
			}
			
			if (headers.constructor === [].constructor) {
				console.log("El header enviado no es un JSON Array");
				error = true;
			}*/
			if (headers.length === 0) {
				console.log("Debe haber al menos un header...");
				error = true;
			}
			
			if (error) 
				return null;
			
			var tabla = { "body": data, "headers": headers };
			
			//return JSON.jsonificar(tabla);
			return tabla;
		}
	}
})();
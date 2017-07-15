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
			
			//if (JSON.isJSONArray(data)) {
				//error = true;
			//}
			
			//if (JSON.isJSONArray(headers)) {
				//error = true;
			//}
			
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
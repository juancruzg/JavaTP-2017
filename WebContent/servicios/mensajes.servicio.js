(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.service('$mensajes', mensajes);
	
	mensajes.$inject = ['ngToast'];
	
	function mensajes(ngToast) {
		var servicio = this;
		
		servicio.mostrarError = mostrarError;
		servicio.mostrarErrores = mostrarErrores;
		servicio.mostrarExito = mostrarExito;
		
		function mostrarError(html) {
			ngToast.create({
    			'content': html,
    			'className': "danger",
    			'dismissButton': true
    		});
		}
		
		function mostrarErrores(listaErrores) {
			var msg = "<ul class='mensaje fa-ul'>";
			listaErrores.forEach(function(error) {
    			msg = msg + "<li><i class='fa-li fa fa-exclamation-triangle'></i>" + error.mensajeError + "</li>";
    		});
    		
			msg = msg + "</ul>"
    		
    		mostrarError(msg);
		}
		
		function mostrarExito(html) {
			ngToast.create({
				'content': html,
				'className': "success",
				'dismissButton': true
			});
		}
	}
})();
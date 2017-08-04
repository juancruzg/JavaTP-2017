(function() {
	'use strict';

	angular
		.module('shop-management')
		.service('$api', servicioApi);
  
	servicioApi.$inject = ["$http", "$q", "$mensajes"];
	  
	function servicioApi ($http, $q, $mensajes) {
		this.getData = function (url, data) {
			var deferred = $q.defer();
			var queryString = "";
		
			// Si viene data, genero el QueryString
			if (data) {			
				for (var properties in data) {
					if (queryString != "")
						queryString = queryString + "&" + properties + "=" + data[properties];
					else
						queryString = "?" + properties + "=" + data[properties];
				}
			}
			
			$http({
				method: 'GET',
				url: url + queryString
			})
			.then(function(data) {
				if (data.data.errores) {
					$mensajes.mostrarErroress(data.data.errores);
		    		deferred.reject;
		    	}
		    	else
		    		deferred.resolve(data.data.data);
			})
			.catch(function(info) {
				if(info.status == 404);
				deferred.reject;
			});
		
			return deferred.promise;
		};
	
		this.postData = function (url, data) {
			var deferred = $q.defer();
	  	
		  	$http({
		        method: 'POST',
		        url: url,
		        dataType: 'json',
		        headers: {'Content-Type': 'application/json'},
		        data: JSON.stringify(data),
		    })
		    .then(function(data) {
		    	if (data.data.errores) {
		    		$mensajes.mostrarErrores(data.data.errores);
		    		deferred.reject;
		    	}
		    	else
		    		deferred.resolve(data.data.data);
		  	})
		  	.catch(function(info) {
		  		if(info.status == 404)
		  		deferred.reject;
		  	});
		  	
		  	return deferred.promise;
		};
	
		this.putData = function (url, data) {
			var deferred = $q.defer();
	  	
		  	$http({
		        method: 'PUT',
		        url: url,
		        dataType: 'json',
		        headers: {'Content-Type': 'application/json'},
		        data: JSON.stringify(data),
		    })
		    .then(function(data) {
		    	if (data.data.errores) {
		    		$mensajes.mostrarErrores(data.data.errores);
		    		deferred.reject;
		    	}
		    	else
		    		deferred.resolve(data.data.data);
		  	})
		  	.catch(function(info) {
		  		if(info.status == 404)
		  		deferred.reject;
		  	});
		  	
		  	return deferred.promise;
		};
	
		this.deleteData = function (url, data) {
			var deferred = $q.defer();
		  	
		  	$http({
		        method: 'DELETE',
		        url: url,
		        dataType: 'json',
		        headers: {'Content-Type': 'application/json'},
			            data: JSON.stringify(data),
			        })
		        .then(function(data) {
		        	if (data.data.errores) {
		        		$mensajes.mostrarErrores(data.data.errores);
			    		deferred.reject;
			    	}
			    	else
			    		deferred.resolve(data.data.data);
		      	})
		      	.catch(function(info) {
		      		if(info.status == 404)
		      		deferred.reject;
		      	});
		      	
	      	return deferred.promise;
    	};
	}
})();
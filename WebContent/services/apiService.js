app.service('apiService', function($http, $q) {
	 
    this.getData = function (url, data) {
		 	var deferred = $q.defer();
	      	$http({
	            method : 'GET',
	            url : url + "?idCliente=" + data,
	        }).then(function(data) {
	      		deferred.resolve(data);
	      	}).catch(function(info) {
	      		if(info.status == 404)
	      			/*ESTE EJEMPLO ES PARA MOSTRAR ALGO SEGUN EL CODIGO DE ERROR QUE DEVUELVA EL SERVLET, DISCUTIR..*/
	      		deferred.reject;
	      	});
	      		return deferred.promise;
      };
	
    this.postData = function (url, data) {
	      	var deferred = $q.defer();
	      	$http({
	            method : 'POST',
	            url : url,
	            data : data,
	        }).then(function(data) {
	      		deferred.resolve(data);
	      	}).catch(function(info) {
	      		if(info.status == 404)
	      			/*ESTE EJEMPLO ES PARA MOSTRAR ALGO SEGUN EL CODIGO DE ERROR QUE DEVUELVA EL SERVLET, DISCUTIR..*/
	      		deferred.reject;
	      	});
	      		return deferred.promise;
        };
});
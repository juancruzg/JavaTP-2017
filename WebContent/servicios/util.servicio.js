(function() {
	'use strict';

	angular
		.module('shop-management')
		.service('$util', servicioUtil);
	
	servicioUtil.$inject = [ '$timeout', '$window' ];
	
	function servicioUtil ($timeout, $window) {
		this.scrollTo = scrollTo;
		this.focus = focus;
		this.containsObject = containsObject;
		
		function scrollTo(element, to, duration) {
		    if (duration <= 0) 
		    	return;
		    
		    var difference = to - element.scrollTop;
		    var perTick = difference / duration * 10;

		    setTimeout(function() {
		        element.scrollTop = element.scrollTop + perTick;
		        
		        if (element.scrollTop === to) 
		        	return;
		        
		        scrollTo(element, to, duration - 10);
		    }, 10);
		}
		
		function focus(id) {
	      $timeout(function() {
	        var element = $window.document.getElementById(id);
	        
	        if(element)
	          element.focus();
	      });
	    }
		
		function containsObject(obj, list) {
			var isIdentical;
			list.forEach(function(o) {	
				isIdentical = true;
				
				for(var propName in o) {
					if(o[propName] !== obj[propName]) {
						isIdentical = false;			
						break;
					}
				}
				
				if (isIdentical)
					return;
			});
			
			return isIdentical;
		}
	}
})();
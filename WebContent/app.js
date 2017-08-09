var app = angular.module('shop-management', ['ui.router', 'ngAnimate', 'ngSanitize', 'ngToast', 'cfp.hotkeys']);

app
.config(['ngToastProvider', function(ngToast) {
    ngToast.configure({
      animation: 'slide',
      maxNumber: 0,
      combineDuplications: true
    });
  }])
.config(function($stateProvider, $urlRouterProvider, $usuarioProvider) {
	var usuario = $usuarioProvider.$get().getUsuarioConectado();

	var home = {
		name: "home",
		templateUrl: "./areas/home/home.html",
		url: "/home"
	}
	
	var clientes = {
		name: "clientes",
		templateUrl: "./areas/clientes/clientes.html",
		url: "/clientes",
		controller: "controladorClientes",
		controllerAs: "VMClientes"
	}
	
	var usuarios = {
		name: "usuarios",
		templateUrl: "./areas/usuarios/usuarios.html",
		url: "/usuarios",
		controller: "controladorUsuarios",
		controllerAs: "VMUsuarios"
	}
	
	var editarClientes = {
		name: "clientes.editar",
		templateUrl: "./areas/clientes/clientes.editar.html",
		url: "/editar/:idCliente",
		params: {cliente: null},
		controller: "controladorClientesEditar",
		controllerAs: "VMClientesEditar"
	}
	
	var nuevaVenta = {
		name: "nueva-venta",
		templateUrl: "./areas/ventas/nueva-venta.html",
		url: "/nueva-venta",
		controller: "controladorNuevaVenta",
		controllerAs: "VMNuevaVenta"
	}
	
	var productos = {
		name: "productos",
		templateUrl: "./areas/productos/productos.html", 
		url: "/productos",
		controller: "controladorProductos",
		controllerAs: "VMProductos"
	} 
	
	var editarProductos = {
		name: "productos.editar",
		templateUrl: "./areas/productos/productos.editar.html", 
		url: "/editar/:idProducto",
		controller: "controladorProductosEditar",
		controllerAs: "VMProductosEditar"
	} 
	
	var login = {
		name: "login",
		templateUrl: "./areas/login/login.html",
		url: "/login",
		controller: "controladorLogin",
		controllerAs: "VMLogin"
	}
	
	var notLoggedIn = {
        security: ['$q', function($q){
            if(!usuario){
               return $q.reject("Not Authorized");
            }
        }]
     };
	
	home.resolve = clientes.resolve = usuarios.resolve = editarClientes.resolve = nuevaVenta.resolve = productos.resolve = editarProductos.resolve = notLoggedIn;
	
	var defaultUrl = '/login';
	
	if (usuario)
		defaultUrl = '/home';
	
	$urlRouterProvider.otherwise(defaultUrl);
	
	$stateProvider
		.state(home)
		.state(clientes)
		.state(usuarios)
		.state(editarClientes)
		.state(nuevaVenta)
		.state(productos)
		.state(editarProductos)
		.state(login);
})
.config(function(hotkeysProvider) {
	hotkeysProvider.cheatSheetDescription = "Mostrar/Ocultar este menú de ayuda";
	hotkeysProvider.templateTitle = "Puede usar los siguientes controles para una mejor experiencia:"
}).run(function($transitions, $state) {
	$transitions.onError({}, function(t) {
	    if(t.error().detail === "Not Authorized"){
	        $state.go("login");
	    }
	});
}).run(function() {
	// Esto lo intenté hacer en un servicio, pero no estoy seguro por qué, primero que el servicio cargaban las vistas, entonces en la primera carga tiraba error, pero en las siguientes no
	// Si alguien sabe por qué pasa esto, acomódelo en el servicio Útil. De última acá no molesta tampoco
	Date.prototype.add = function(cantidad, tipo) {
		var fecha = new Date(this.valueOf());	
	
		if (tipo === 'd') {
			fecha.setDate(fecha.getDate() + cantidad);
		}
		else if (tipo === 'w') {
			fecha.setDate(fecha.getDate() + (7 * cantidad));
		}
		else if (tipo ==='m') {
			fecha.setMonth(fecha.getMonth() + cantidad);
		}
	
		return fecha;
	}
		
	// Extend Date functions
	Date.prototype.getDateName = function() {
		var fecha = new Date(this.valueOf());
	  
		var dias = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
	
		return dias[fecha.getDay()];
	}
	
	Date.prototype.getMonthName = function() {
		var fecha = new Date(this.valueOf());
		
		var monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
			"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

		return monthNames[fecha.getMonth()];
	}
  
	Date.prototype.toString = function() {
		var fecha = new Date(this.valueOf());
	  
		return fecha.getDate() + ' de ' + fecha.getMonthName() + ' de ' + fecha.getFullYear();
	}
});

app.controller('indexController', indexController);

indexController.$inject = ["hotkeys", "$usuario"];

function indexController(hotkeys, $usuario) {
	var vm = this;
	vm.toggleCheatSheet = hotkeys.toggleCheatSheet;
	vm.menuToggled = false;
	vm.toggleMenu = toggleMenu;
	vm.mostrarMenu = false;  
	
	var usuario = $usuario.getUsuarioConectado();
	
	if (usuario) {
		vm.mostrarMenu = true;
	
		hotkeys.add({
			combo: '-',
		    description: 'Expandir menú principal',
		    callback: function() {
		      toggleMenu();
		    }
		});
	} 
	
	function toggleMenu() {
	    vm.menuToggled = !vm.menuToggled;
	}
}

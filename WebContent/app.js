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
	var home = {
		name: "home",
		templateUrl: "./areas/home/home.html",
		url: "/home",
		controller: "controladorHome",
		controllerAs: "VMHome"
	}
	
	var error = {
		name: "error",
		templateUrl: "./areas/error/error.html",
		url: "/error"
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
	
	var editarUsuarios = {
		name: "usuarios.editar",
		templateUrl: "./areas/usuarios/usuarios.editar.html",
		url: "/editar/:loginUsuario",
		params: { usuario: null },
		controller: "controladorUsuariosEditar",
		controllerAs: "VMUsuariosEditar"
	}
	
	var editarClientes = {
		name: "clientes.editar",
		templateUrl: "./areas/clientes/clientes.editar.html",
		url: "/editar/:idCliente",
		params: { cliente: null },
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
		params: { producto: null },
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
	
	var perfil = {
		name: "perfil",
		templateUrl: "./areas/perfil/perfil.html",
		url: "/perfil",
		controller: "controladorPerfil",
		controllerAs: "VMPerfil"	
	}
	
	var reporte = {
			name: "reporte",
			templateUrl: "./areas/reportes/reportes.html",
			url: "/reporte",
			controller: "controladorReportes",
			controllerAs: "VMReporte"	
		}
	
	var reporteVenta = {
			name: "reporte.venta",
			templateUrl: "./areas/reportes/reportes.ventas.html",
			url: "/venta",
			params: { detalle: null },
			controller: "controladorReportesVenta",
			controllerAs: "VMReporteVenta"	
		}
		
	var defaultUrl = '/home';

	$urlRouterProvider.otherwise(defaultUrl);
	
	$stateProvider
		.state(home)
		.state(clientes)
		.state(usuarios)
		.state(editarUsuarios)
		.state(editarClientes)
		.state(nuevaVenta)
		.state(productos)
		.state(editarProductos)
		.state(login)
		.state(perfil)
		.state(reporte)
		.state(reporteVenta)
		.state(error);
})
.run(['$transitions', '$usuario', '$state', function($transitions, $usuario, $state) {
	$transitions.onBefore({}, function(tran) {
		var to = tran.$to();
		
		if (to.name != "error" && to.name != "login") {			
			return $usuario.permitir(to.name).catch(function(target) {
				return $state.target(target);
			});
		}
	}); 
}])
.config(function(hotkeysProvider) {
	hotkeysProvider.cheatSheetDescription = "Mostrar/Ocultar este menú de ayuda";
	hotkeysProvider.templateTitle = "Puede usar los siguientes controles para una mejor experiencia:"
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

indexController.$inject = ["hotkeys", "$usuario", "$scope", "$rootScope"];

function indexController(hotkeys, $usuario, $scope, $rootScope) {
	var vm = this;
	vm.toggleCheatSheet = hotkeys.toggleCheatSheet;
	vm.menuToggled = false;
	vm.toggleMenu = toggleMenu;
	vm.mostrarMenu = false;  
	vm.salir = salir;
	vm.itemsMenu = [];
	
	$usuario.isLoggedIn().then(function(usuario) {
		vm.mostrarMenu = !!usuario;
		vm.menuToggled = !!usuario;
				
		if (usuario) {	
			vm.itemsMenu = usuario.tipoUsuario.modulos;

			hotkeys.add({
				combo: '-',
			    description: 'Mostrar/Ocultar menú principal',
			    callback: function() {
			      toggleMenu();
			    }
			});
		} 
	});
	
	$rootScope.$on('login', function(ev, usuario) {
		vm.mostrarMenu = !!usuario;
		vm.menuToggled = !!usuario;
		
		if (usuario) {		
			vm.itemsMenu = usuario.tipoUsuario.modulos;

			hotkeys.add({
				combo: '-',
			    description: 'Expandir menú principal',
			    callback: function() {
			      toggleMenu();
			    }
			});
		} 
	});
	
	function salir() {
		$usuario.logout();
		vm.mostrarMenu = false;
		vm.menuToggled = false;
	}
	
	function toggleMenu() {
	    vm.menuToggled = !vm.menuToggled;
	}
}

(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorNuevaVenta', controladorNuevaVenta);
	
	controladorNuevaVenta.$inject = ['$scope', '$api', '$tabla', '$q', '$util'];
	
	function controladorNuevaVenta($scope, $api, $tabla, $q, $util) {
		var vm = this;
		
		// Clientes
		vm.listarClientes = listarClientes;
		vm.accionClientes = accionClientes;
		vm.limpiarCliente = limpiarCliente;
		vm.clienteSeleccionado = null;
		
		// Productos
		vm.listarProductos = listarProductos;
		vm.accionProductos = accionProductos;
		vm.limpiarProducto = limpiarProducto;
		vm.agregarProductoSeleccionado = agregarProductoSeleccionado;
		vm.productoSeleccionado = null;
		vm.listaProductos = [];
		vm.quitarUnProducto = quitarUnProducto;
		
		vm.fecha = new Date();
		vm.cantidad = 1;
		
		vm.seleccionarColor = seleccionarColor;

		vm.colores = [];
		vm.talles = [];
		
		// Clientes
		function limpiarCliente() {
			vm.clienteSeleccionado = null;
		}		
		
		function accionClientes(cliente) {
			vm.clienteSeleccionado = cliente;
		}
	  
		function listarClientes(texto) {
			var deferred = $q.defer();
		  
			var data = {
				'paginaActual': 0,
				'porPagina': 10,
				'query': texto
			};
			
			var output = {};
		  
			$api.getData("Clientes", data).then(function(data) {
				output.data = data;
				output.columnasAMostrar = ["nombre", "apellido"];
				  	
				deferred.resolve(output);
			});
			  		  
			return deferred.promise;
		}
		
		// Productos
		function accionProductos(p) {
			vm.colores = [];
			
			p.lineas.forEach(function (lp) {
				if (!$util.containsObject(lp.color, vm.colores))
					vm.colores.push(lp.color);
			});
				
			vm.productoSeleccionado = p;
		}
		
		function seleccionarColor() {
			var idColor = vm.colorSeleccionado.id;
			
			vm.talles = [];
			
			vm.productoSeleccionado.lineas.forEach(function (p) {
				if ((!$util.containsObject(p.talle, vm.talles)) && p.color.id === idColor)
					vm.talles.push(p.talle);
			});
		}
		
		function listarProductos(texto) {
			var deferred = $q.defer();
			
			var data = {
				'paginaActual': 0,
				'porPagina': 10,
				'query': texto
			}
			
			var output = {};
			
			$api.getData("Productos", data).then(function(data) {
				output.data = data;
				output.columnasAMostrar = ["descripcion"];
				
				deferred.resolve(output);
			});
			
			return deferred.promise;
		}
		
		function agregarProductoSeleccionado() {
			// Si tengo todos los select asignados.
			if (vm.productoSeleccionado && vm.colorSeleccionado && vm.talleSeleccionado) {
				var producto = {};
				
				producto.color = vm.colorSeleccionado;
				producto.talle = vm.talleSeleccionado;

				producto.id = vm.productoSeleccionado.id;
				producto.descripcion = vm.productoSeleccionado.descripcion;
				producto.precio = vm.productoSeleccionado.precio.precio;
				
				var productoDistinto = true;
				
				// Me fijo que no haya seleccionado antes el mismo producto, y línea.
				vm.listaProductos.forEach(function(p) {
					if (producto.id === p.id && producto.color.id === p.color.id && producto.talle.id === p.talle.id) {
						// De ser así, le sumo la cantidad
						p.cantidad = p.cantidad + vm.cantidad;
						productoDistinto = false;
						return;
					}
				});	
				
				if (productoDistinto) {
					producto.cantidad = vm.cantidad;
					vm.listaProductos.push(producto);
				}
			
				// Al final calculo el total.
				calcularTotal();
			}
		}
		
		function limpiarProducto() {
			vm.talles = [];
			vm.colores = [];
			vm.productoSeleccionado = null;
		}
		
		function quitarUnProducto(producto) {
			vm.listaProductos.forEach(function(p, index, object) {
				if (p.id === producto.id && p.color.id === producto.color.id && p.talle.id === producto.talle.id) {
					if (p.cantidad > 1)
						p.cantidad --;
					else
						object.splice(index, 1);
				}
			});
			
			calcularTotal();
		}
		
		function calcularTotal() {
			var total = 0;
			
			vm.listaProductos.forEach(function(p) {
				total = total + (p.precio * p.cantidad)
			});
			
			vm.listaProductos.total = total;
		}
	}
})();
(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorNuevaVenta', controladorNuevaVenta);
	
	controladorNuevaVenta.$inject = ['$scope', '$api', '$tabla', '$q', '$util', '$mensajes'];
	
	function controladorNuevaVenta($scope, $api, $tabla, $q, $util, $mensajes) {
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
		vm.listaDetalles = [];
		vm.quitarUnProducto = quitarUnProducto;
		vm.controlProductos = {};
		
		vm.fecha = new Date();
		vm.cantidad = 1;
		
		vm.seleccionarColor = seleccionarColor;

		vm.colores = [];
		vm.talles = [];
		
		vm.finalizar = finalizar;
		
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
			if (vm.colorSeleccionado) {
				var idColor = vm.colorSeleccionado.id;
				
				vm.talles = [];
				
				vm.productoSeleccionado.lineas.forEach(function (p) {
					if ((!$util.containsObject(p.talle, vm.talles)) && p.color.id === idColor)
						vm.talles.push(p.talle);
				});
			}
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
		
		/*function agregarProductoSeleccionado() {
			// Si tengo todos los select asignados.
			if (vm.productoSeleccionado && vm.colorSeleccionado && vm.talleSeleccionado) {
				var producto = {};
				
				producto.color = vm.colorSeleccionado;
				producto.talle = vm.talleSeleccionado;
	
				producto.id = vm.productoSeleccionado.id;
				producto.descripcion = vm.productoSeleccionado.descripcion;
				producto.precio = vm.productoSeleccionado.precio.precio;
				
				var productoDistinto = true;
				var lineaSeleccionada;
				
				vm.productoSeleccionado.lineas.forEach(function(l) {
					if (l.color.id === producto.color.id && l.talle.id === producto.talle.id) {
						lineaSeleccionada = l;
						return;
					}
				});
				
				var productoActual = null;
				var stock = 0;
				
				// Me fijo que no haya seleccionado antes el mismo producto, y línea.
				vm.listaProductos.forEach(function(p) {
					if (producto.id === p.id && producto.color.id === p.color.id && producto.talle.id === p.talle.id) {
						
						productoActual = p;
						return;
					}
				});	

				if(productoActual && productoActual.cantidad)
					stock = productoActual.cantidad + vm.cantidad;
				else
					stock = vm.cantidad;
				
				if (stock <= lineaSeleccionada.stock) {
					if (!productoActual) {
						producto.cantidad = vm.cantidad;
						vm.listaProductos.push(producto);
					}
					else
						productoActual.cantidad = productoActual.cantidad + vm.cantidad;
						
					// Al final calculo el total.
					calcularTotal();
				
					vm.controlProductos.quitarItem();
				}
				else {
					var error = "No hay suficiente stock del producto seleccionado."
					$mensajes.mostrarError(error);
				}	
			}
		}*/
		
		function agregarProductoSeleccionado() {
			// Si tengo todos los select asignados.
			if (vm.productoSeleccionado && vm.colorSeleccionado && vm.talleSeleccionado) {
				var detalle = {};
				var talle = vm.talleSeleccionado;
				var color = vm.colorSeleccionado;
				
				var detalleDistinto = true;
				var detalleActual = null;
				var lineaSeleccionada;
				var stock = 0;
				
				vm.productoSeleccionado.lineas.forEach(function(l) {
					if (l.color.id === color.id && l.talle.id === talle.id) {
						lineaSeleccionada = l;
						return;
					}
				});
				
				detalle.linea = lineaSeleccionada;
				detalle.linea.producto = vm.productoSeleccionado;
				
				// Me fijo que no haya seleccionado antes el mismo producto, y línea.
				vm.listaDetalles.forEach(function(d) {
					if (detalle.linea.producto.id === d.linea.producto.id && detalle.linea.color.id === d.linea.color.id && detalle.linea.talle.id === d.linea.talle.id && detalle.linea.sucursal.id === d.linea.sucursal.id) {
						detalleActual = d;
						return;
					}
				});	

				if(detalleActual && detalleActual.linea.cantidad)
					stock = detalleActual.linea.cantidad + vm.cantidad;
				else
					stock = vm.cantidad;
				
				if (stock <= lineaSeleccionada.stock) {
					if (!detalleActual) {
						detalle.cantidad = vm.cantidad;
						vm.listaDetalles.push(detalle);
					}
					else
						detalleActual.cantidad = detalleActual.cantidad + vm.cantidad;
						
					// Al final calculo el total.
					calcularTotal();
				
					vm.controlProductos.quitarItem();
				}
				else {
					var error = "No hay suficiente stock del producto seleccionado."
					$mensajes.mostrarError(error);
				}	
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
			
			vm.listaDetalles.forEach(function(d) {
				total = total + (d.linea.producto.precio.precio * d.cantidad)
			});
			
			vm.listaDetalles.total = total;
		}
		
		function finalizar() {
			var venta = {};			
			
			venta.cliente = vm.clienteSeleccionado;
			venta.fecha = vm.fecha;
			venta.tipoPago = 1;//vm.tipoPagoSeleccionado;
			venta.detalles = vm.listaDetalles;
			debugger;
			//$api.postData("Ventas", venta);
		}
	}
})();
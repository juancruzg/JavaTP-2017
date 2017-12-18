(function() {
	'use strict';
	
	angular
		.module('shop-management')
		.controller('controladorNuevaVenta', controladorNuevaVenta);
	
	controladorNuevaVenta.$inject = ['$scope', '$api', '$tabla', '$q', '$util', '$mensajes', '$state'];
	
	function controladorNuevaVenta($scope, $api, $tabla, $q, $util, $mensajes, $state) {
		var vm = this;
		
		// Clientes
		vm.listarClientes = listarClientes;
		vm.accionClientes = accionClientes;
		vm.limpiarCliente = limpiarCliente;
		vm.clienteSeleccionado = null;
		vm.controlClientes = {};
		
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
		vm.ingresaMonto = ingresaMonto;

		vm.colores = [];
		vm.talles = [];
		vm.tiposPago = [];
		
		vm.finalizar = finalizar;
		
		listarTiposPago();
		
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
		
		function ingresaMonto(){
			vm.vuelto = vm.monto - vm.listaDetalles.total;
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
				output.columnasAMostrar = [ "descripcion", "marca" ];
				
				deferred.resolve(output);
			});
			
			return deferred.promise;
		}
		
		function listarTiposPago(){
			$api.getData("TiposPago").then(function(data){
				vm.tiposPago = data;
			});
		}
		
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
				detalle.linea.producto.id = vm.productoSeleccionado.id;
				
				// Me fijo que no haya seleccionado antes el mismo producto, y línea.
				vm.listaDetalles.forEach(function(d) {
					if (vm.productoSeleccionado.id === d.linea.producto.id && lineaSeleccionada.color.id === d.linea.color.id && lineaSeleccionada.talle.id === d.linea.talle.id && lineaSeleccionada.sucursal.id === d.linea.sucursal.id) {
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
		
		function quitarUnProducto(detalle) {
			vm.listaDetalles.forEach(function(d, index, object) {
				if (d.linea.producto.id === detalle.linea.producto.id && d.linea.color.id === detalle.linea.color.id && d.linea.talle.id === detalle.linea.talle.id && d.linea.sucursal.id === detalle.linea.sucursal.id) {
					if (d.cantidad > 1)
						d.cantidad --;
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
			var modeloPago = {
				tipoPago:{
					id: vm.tipoPagoSeleccionado.id,
					tipoPago: vm.tipoPagoSeleccionado.tipoPago
				},
				nroTarjeta: vm.numeroTarjeta,
				nombreTitular: vm.nombreTitularTarjeta,
				vencimientoTarjeta: vm.mesTarjeta,
				fechaPago: new Date(),
				monto: parseFloat(vm.monto)
			}
			venta.cliente = vm.clienteSeleccionado;
			venta.fecha = vm.fecha;
			venta.detalles = vm.listaDetalles;
	
			venta.pagos = [];
			
			venta.pagos.push({ 
				tipoPago: vm.tipoPagoSeleccionado,
				fechaPago: vm.fecha
			});
			var modeloModeloPagoVenta = {
				venta: venta,
				modeloPago: modeloPago
			};
			$api.postData("Ventas", modeloModeloPagoVenta).then(function(data) {
				$state.reload();
				$mensajes.mostrarExito("Venta realizada correctamente.");
			});
		}	
	}
})();
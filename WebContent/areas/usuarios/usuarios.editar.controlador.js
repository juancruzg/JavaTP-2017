(function() {
  'use strict';
  
  angular
      .module('shop-management')
      .controller('controladorUsuariosEditar', controladorUsuariosEditar);

  controladorUsuariosEditar.$inject = ["$scope", "$state", "$api", "$tabla", "$q", "$stateParams", "$util", "hotkeys"];

  function controladorUsuariosEditar($scope, $state, $api, $tabla, $q, $stateParams, $util, hotkeys) {
	  var vm = this;
	  
	  // Ni bien carga el controller, scrolleo hasta el fondo y hago focus al nombre.
	  $util.scrollTo(document.body, document.body.scrollHeight, 500);	
	  $util.focus("nombre");
	  
	  vm.guardar = guardar;
	  vm.cancelar = cancelar;
	  vm.listaTipos = [];
	  
	  listarTipos();
	  seleccionarUsuario();

	  hotkeys.bindTo($scope).add({
		  combo: 'esc',
		  description: 'Cancelar',
		  allowIn: ['INPUT'],
		  callback: cancelar
      });
	  
	  function guardar() {
		  var promesa;
		  
		  promesa = $api.postData("Usuarios", vm.usuarioSeleccionado);
		 
		  promesa.then(function (data) {
			  $state.go('usuarios', null, { 'reload': true });	  
		  });
	  }
	  
	  function cancelar() {
		  $state.go('^');
		  $util.scrollTo(document.body, 0, 500);
	  }
	  
	  function listarTipos() {
		  $api.getData("TiposUsuario").then(function (data) {
			 vm.listaTipos = data; 
		  });
	  }
	  
	  function seleccionarUsuario() {
		  if (!$stateParams.loginUsuario) {
			  vm.usuarioSeleccionado = {};
			  vm.usuarioSeleccionado.activo = true;
			  return;
		  }
		  
		  if ($stateParams.usuario) {
			  vm.usuarioSeleccionado = $stateParams.usuario;
			  return;
		  }
		  else
			  buscarUsuario($stateParams.loginUsuario);
		  
		  function buscarUsuario(usuario) {
			  $api.getData("Usuarios", { "usuario": usuario }).then(function(usuario) {
				  if (usuario)
					  vm.usuarioSeleccionado = usuario;
				  else {
					  vm.usuarioSeleccionado = {};
					  vm.usuarioSeleccionado.activo = true;
				  }
			  });
		  }
	  }
  }
})();
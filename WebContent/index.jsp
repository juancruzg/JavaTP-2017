 <%@ page 
	language = "java" 
	contentType = "text/html; charset=UTF-8"
    pageEncoding = "UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Negocio Ropa v1.0</title>
	
		<link href="./lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="./lib/ng-toast/ngToast.min.css" rel="stylesheet">
		<link href="./lib/hotkeys/hotkeys.min.css" rel="stylesheet">
		<link href="./lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="./lib/custom/app.css" rel="stylesheet">
		<link href="./lib/custom/modal.css" rel="stylesheet">
		<link href="./directivas/calendario/calendario.css" rel="stylesheet">			
		<link href="./areas/login/login.css">
	</head>
	<body>
  	<body ng-app="shop-management" ng-controller="indexController as indexVm">
  		<toast></toast>
  		<div id="wrapper" ng-class="{'toggled': indexVm.menuToggled}">
			<div id="sidebar-wrapper" ng-if="indexVm.mostrarMenu">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">
	                    <a>Negocio de Ropa</a>
	              	</li>
	              	<li>
                  		<a ui-sref="home"><i class="fa fa-home" aria-hidden="true"></i> Inicio</a>
					</li>
					<li>
					    <a ui-sref="clientes"><i class="fa fa-users" aria-hidden="true"></i> Clientes</a>
					</li>
					<li>
					    <a ui-sref="usuarios"><i class="fa fa-users" aria-hidden="true"></i> Usuarios</a>
					</li>
					<li>
					    <a ui-sref="productos"><i class="fa fa-user-secret" aria-hidden="true"></i> Productos</a>
					</li>
					<li>
					    <a ui-sref="nueva-venta"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Nueva Venta</a>
					</li>
              </ul>
			</div>
			<div id="page-content-wrapper">
              <div class="fluid-row">
                  <div class="col-sm-1">
                      <a class="toggle-button" ng-click="indexVm.toggleMenu()"><i class="fa fa-3x" ng-class="{'fa-bars': !indexVm.menuToggled, 'fa-times': indexVm.menuToggled}" aria-hidden="true" style="position:fixed;" ng-if="indexVm.mostrarMenu"></i></a>
                  </div>
                  <div class="col-sm-10">
                      <div style="height: 25px;"></div>
                      <ui-view></ui-view>
                  </div>
                  <div class="col-sm-1">
                    <a class="toggle-button" ng-click="indexVm.toggleCheatSheet()" style="position:fixed;"><i class="fa fa-question-circle fa-3x"></i></a>
                  </div>
              </div>
          </div>
		</div>
	</body>
</html>

<script type="text/javascript" src="./lib/angular/angular.min.js"></script>
<script type="text/javascript" src="./lib/angular/angular-animate.min.js"></script>
<script type="text/javascript" src="./lib/angular/angular-sanitize.min.js"></script>

<script type="text/javascript" src="./lib/ui-router/angular-ui-router.min.js"></script>
<script type="text/javascript" src="./lib/ng-toast/ngToast.min.js"></script>
<script type="text/javascript" src="./lib/hotkeys/hotkeys.min.js"></script>

<script type="text/javascript" src="./app.js"></script>

<!-- SERVICES -->
<script type="text/javascript" src="./servicios/mensajes.servicio.js"></script>
<script type="text/javascript" src="./servicios/util.servicio.js"></script>
<script type="text/javascript" src="./servicios/api.servicio.js"></script>
<script type="text/javascript" src="./servicios/tabla.servicio.js"></script>
<script type="text/javascript" src="./servicios/usuario.servicio.js"></script>

<!-- DIRECTIVES -->
<script type="text/javascript" src="./directivas/modal/modal.directiva.js"></script>
<script type="text/javascript" src="./directivas/modal/modal.controlador.js"></script>
<script type="text/javascript" src="./directivas/tabla/tabla.directiva.js"></script>
<script type="text/javascript" src="./directivas/tabla/tabla.controlador.js"></script>
<script type="text/javascript" src="./directivas/selector/selector.directiva.js"></script>
<script type="text/javascript" src="./directivas/selector/selector.controlador.js"></script>
<script type="text/javascript" src="./directivas/calendario/calendario.directiva.js"></script>
<script type="text/javascript" src="./directivas/calendario/calendario.controlador.js"></script>
<script type="text/javascript" src="./directivas/selector-fecha/selector-fecha.directiva.js"></script>
<script type="text/javascript" src="./directivas/selector-fecha/selector-fecha.controlador.js"></script>

<!-- ABM's CONTROLLERS -->
<script type="text/javascript" src="./areas/clientes/clientes.controlador.js"></script>
<script type="text/javascript" src="./areas/clientes/clientes.editar.controlador.js"></script>
<script type="text/javascript" src="./areas/productos/productos.controlador.js"></script>
<script type="text/javascript" src="./areas/productos/productos.editar.controlador.js"></script>
<script type="text/javascript" src="./areas/usuarios/usuarios.controlador.js"></script>
<script type="text/javascript" src="./areas/ventas/nueva-venta.controlador.js"></script>
<script type="text/javascript" src="./areas/login/login.controlador.js"></script>





<%@ page 
	language = "java" 
	contentType = "text/html; charset=UTF-8"
    pageEncoding = "UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Negocio Ropa v1.0</title>
	
		<link href="./lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="./lib/hotkeys/hotkeys.min.css" rel="stylesheet">
		<link href="./lib/custom/app.css" rel="stylesheet">
		<link href="./lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	</head>
	<body>
  	<body ng-app="shop-management" ng-controller="indexController as indexVm">
  		<div id="wrapper" ng-class="{'toggled': indexVm.menuToggled}">
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">
	                    <a>Negocio de Ropa</a>
	              	</li>
	              	<li>
                  		<a ui-sref="home"><i class="fa fa-home" aria-hidden="true"></i> Inicio</a>
					</li>
					<li>
					    <a ui-sref="clients"><i class="fa fa-users" aria-hidden="true"></i> Clientes</a>
					</li>
					<li>
					    <a ui-sref="products"><i class="fa fa-user-secret" aria-hidden="true"></i> Productos</a>
					</li>
					<li>
					    <a ui-sref="sales"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Ventas</a>
					</li>
					<li>
				  		<a><i class="fa fa-sign-out" aria-hidden="true"></i> Exit</a>
					</li>
              </ul>
			</div>
			<div id="page-content-wrapper">
              <div class="fluid-row">
                  <div class="col-sm-1">
                      <a class="toggle-button" ng-click="indexVm.toggleMenu()"><i class="fa fa-3x" ng-class="{'fa-bars': !indexVm.menuToggled, 'fa-times': indexVm.menuToggled}" aria-hidden="true" style="position:fixed;"></i></a>
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
<script type="text/javascript" src="./lib/ui-router/angular-ui-router.min.js"></script>
<script type="text/javascript" src="./lib/hotkeys/hotkeys.min.js"></script>
<script type="text/javascript" src="./app.js"></script>

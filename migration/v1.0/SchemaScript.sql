﻿DROP SCHEMA IF EXISTS NegocioRopa;
CREATE SCHEMA NegocioRopa;

USE NegocioRopa;

CREATE TABLE IF NOT EXISTS sucursal (
  id int NOT NULL AUTO_INCREMENT,
  domicilio varchar(100),
  telefono varchar(20),
  activo boolean,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tipoUsuario (
  id int NOT NULL AUTO_INCREMENT,
  descripcion varchar(20),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario (
  usuario varchar(20) NOT NULL,
  password varchar(100), /*le pongo 100 con la intención de encriptar*/
  nombre varchar(50),
  apellido varchar(50),
  idSucursal int,
  idTipoUsuario int,
  usuarioAlta varchar(20),
  fechaAlta datetime,
  activo boolean,
  PRIMARY KEY (usuario),
  CONSTRAINT fk_usuario_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario),
  CONSTRAINT fk_usuario_tipoUsuario FOREIGN KEY (idTipoUsuario) REFERENCES tipoUsuario (id),
  CONSTRAINT fk_usuario_sucursal FOREIGN KEY (idSucursal) REFERENCES sucursal (id)
);

CREATE TABLE IF NOT EXISTS modulo (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(100),
  mostrarEnMenu boolean,
  nombreAMostrar varchar(100),
  claseIcono varchar(50),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tipoUsuarioModulo (
  idTipoUsuario int NOT NULL,
  idModulo int NOT NULL,
  PRIMARY KEY (idTipoUsuario, idModulo),
  CONSTRAINT fk_tipoUsuarioModulo_tipoUsuario FOREIGN KEY (idTipoUsuario) REFERENCES tipoUsuario (id),
  CONSTRAINT fk_tipoUsuarioModulo_modulo FOREIGN KEY (idModulo) REFERENCES modulo (id)
);

CREATE TABLE IF NOT EXISTS cliente (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(50),
  apellido varchar(50),
  domicilio varchar(100),
  telefono varchar(20),
  activo boolean,
  fechaAlta datetime,
  usuarioAlta varchar(20),
  PRIMARY KEY (id),
  CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS producto (
  id int NOT NULL AUTO_INCREMENT,
  descripcion varchar(255),
  marca varchar(50),
  usuarioAlta varchar(20),
  fechaAlta datetime,
  activo boolean,
  PRIMARY KEY (id),
  CONSTRAINT fk_producto_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS talle (
  id int NOT NULL AUTO_INCREMENT,
  talle varchar(3),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS color (
  id int NOT NULL AUTO_INCREMENT,
  color varchar(30),
  codigoColor varchar(30),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lineaProducto (
  idProducto int NOT NULL,
  idTalle int NOT NULL,
  idColor int NOT NULL,
  idSucursal int NOT NULL,
  stock int,
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (idProducto, idTalle, idColor, idSucursal),
  CONSTRAINT fk_lineaProducto_producto FOREIGN KEY (idProducto) REFERENCES producto (id),
  CONSTRAINT fk_lineaProducto_talle FOREIGN KEY (idTalle) REFERENCES talle (id),
  CONSTRAINT fk_lineaProducto_color FOREIGN KEY (idColor) REFERENCES color (id),
  CONSTRAINT fk_lineaProducto_sucursal FOREIGN KEY (idSucursal) REFERENCES sucursal (id),
  CONSTRAINT fk_lineaProducto_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS precio (
  idProducto int NOT NULL,
  fecha datetime,
  precio decimal,
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (idProducto, fecha),
  CONSTRAINT fk_precio_producto FOREIGN KEY (idProducto) REFERENCES producto (id),
  CONSTRAINT fk_precio_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS tipoPago (
  id int NOT NULL AUTO_INCREMENT,
  tipoPago varchar(20),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS estado (
  id int NOT NULL AUTO_INCREMENT,
  estado varchar(20),
  PRIMARY KEY (id)	
);


CREATE TABLE IF NOT EXISTS venta (
  id int NOT NULL AUTO_INCREMENT,
  idCliente int NOT NULL,
  idTipoPago int NOT NULL,
  fecha datetime,
  idEstado int,
  PRIMARY KEY (id),
  CONSTRAINT fk_venta_cliente FOREIGN KEY (idCliente) REFERENCES cliente (id),
  CONSTRAINT fk_venta_tipoPago FOREIGN KEY (idTipoPago) REFERENCES tipoPago (id),
  CONSTRAINT fk_venta_estado FOREIGN KEY (idEstado) REFERENCES estado (id)
);

CREATE TABLE IF NOT EXISTS detalleVenta (
  idVenta int NOT NULL,
  idProducto int NOT NULL,
  idTalle int NOT NULL,
  idColor int NOT NULL,
  idSucursal int NOT NULL,
  cantidad int,
  llevaAProbar boolean,
  fechaDevolucion datetime,
  PRIMARY KEY (idVenta, idProducto, idTalle, idColor, idSucursal),
  CONSTRAINT fk_detalleVenta_venta FOREIGN KEY (idVenta) REFERENCES venta (id),
  CONSTRAINT fk_detalleVenta_lineaProducto FOREIGN KEY (idProducto, idTalle, idColor, idSucursal) REFERENCES lineaProducto (idProducto, idTalle, idColor, idSucursal)
);


INSERT INTO `modulo` VALUES (1,'home',1,'Home','fa fa-home'),(2,'clientes',1,'Clientes','fa fa-black-tie'),(3,'clientes.editar',0,'Editar Clientes',NULL),(4,'usuarios',1,'Usuarios','fa fa-user'),(5,'usuarios.editar',0,'Editar usuarios',NULL),(6,'nueva-venta',1,'Nueva Venta','fa fa-shopping-bag'),(7,'productos',1,'Productos','fa fa-shirtsinbulk'),(8,'productos.editar',0,'Editar Productos',NULL), (9,'perfil',0,'Perfil',NULL);
INSERT INTO `tipousuario` VALUES (1,'Administrador'),(2,'Normal');
INSERT INTO `tipousuariomodulo` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(1,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9);
INSERT INTO `sucursal` (domicilio, telefono) VALUES ('San Lorenzo 123', '0341546103');
INSERT INTO `color` (color) VALUES ('Rojo'), ('Verde'), ('Azul'), ('Amarillo');
INSERT INTO `talle` (talle) VALUES ('XL'), ('L'), ('M'), ('S');
INSERT INTO `usuario` VALUES ('juan_cruzg','9893f9b90ebd9e22f0eba9a8af67d424','Juan Cruz','Grasso',1,1,NULL,NULL,1),('usuarionormal','25d55ad283aa400af464c76d713c07ad','Usuario','Normal',1,2,NULL,NULL,1);
INSERT INTO `tipoPago` (id,tipoPago) VALUES(1,'Efectivo'),(2,'Cuenta corriente'),(3,'Tarjeta'),(4,'Lleva a probar');
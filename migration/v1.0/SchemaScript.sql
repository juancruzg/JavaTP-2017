CREATE SCHEMA IF NOT EXISTS NegocioRopa;

USE NegocioRopa;

CREATE TABLE IF NOT EXISTS sucursal (
  id int NOT NULL AUTO_INCREMENT,
  domicilio varchar(100),
  telefono varchar(20),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario (
  usuario varchar(20) NOT NULL,
  password varchar(100), /*le pongo 100 con la intención de encriptar*/
  nombre varchar(50),
  apellido varchar(50),
  idSucursal int,
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (usuario),
  CONSTRAINT fk_usuario_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario),
  CONSTRAINT fk_usuario_sucursal FOREIGN KEY (idSucursal) REFERENCES sucursal (id)
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
  descripcion varchar(50),
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (id),
  CONSTRAINT fk_producto_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS talle (
  id int NOT NULL AUTO_INCREMENT,
  talle varchar(3),
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (id),
  CONSTRAINT fk_talle_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
);

CREATE TABLE IF NOT EXISTS color (
  id int NOT NULL AUTO_INCREMENT,
  color varchar(30),
  usuarioAlta varchar(20),
  fechaAlta datetime,
  PRIMARY KEY (id),
  CONSTRAINT fk_color_usuario FOREIGN KEY (usuarioAlta) REFERENCES usuario (usuario)
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

CREATE TABLE IF NOT EXISTS venta (
  id int NOT NULL AUTO_INCREMENT,
  idCliente int NOT NULL,
  idTipoPago int NOT NULL,
  fecha datetime,
  PRIMARY KEY (id),
  CONSTRAINT fk_venta_cliente FOREIGN KEY (idCliente) REFERENCES cliente (id),
  CONSTRAINT fk_venta_tipoPago FOREIGN KEY (idTipoPago) REFERENCES tipoPago (id)
);

CREATE TABLE IF NOT EXISTS detalleVenta (
  idVenta int NOT NULL,
  idProducto int NOT NULL,
  cantidad int,
  llevaAProbar boolean,
  fechaDevolucion datetime,
  PRIMARY KEY (idVenta, idProducto),
  CONSTRAINT fk_detalleVenta_venta FOREIGN KEY (idVenta) REFERENCES venta (id),
  CONSTRAINT fk_detalleVenta_producto FOREIGN KEY (idProducto) REFERENCES producto (id)
);

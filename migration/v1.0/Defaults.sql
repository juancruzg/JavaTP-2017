USE NegocioRopa;

INSERT INTO
  `modulo`
VALUES
  (1,'home',1,'Home','fa fa-home'),
  (2,'clientes',1,'Clientes','fa fa-black-tie'),
  (3,'clientes.editar',0,'Editar Clientes',NULL),
  (4,'usuarios',1,'Usuarios','fa fa-user'),
  (5,'usuarios.editar',0,'Editar usuarios',NULL),
  (6,'nueva-venta',1,'Nueva Venta','fa fa-shopping-bag'),
  (7,'productos',1,'Productos','fa fa-shirtsinbulk'),
  (8,'productos.editar',0,'Editar Productos',NULL),
  (9,'perfil',0,'Perfil',NULL);

INSERT INTO
  `tipousuario`
VALUES
  (1,'Administrador'),
  (2,'Normal');

INSERT INTO
  `tipousuariomodulo`
VALUES
  (1,1),
  (2,1),
  (1,2),
  (2,2),
  (1,3),
  (2,3),
  (1,4),
  (1,5),
  (1,6),
  (2,6),
  (1,7),
  (2,7),
  (1,8),
  (2,8),
  (1,9),
  (2,9);

INSERT INTO
  `sucursal` (domicilio, telefono)
VALUES
  ('San Lorenzo 123', '0341546103');

INSERT INTO
  `color` (color)
VALUES
  ('Rojo'),
  ('Verde'),
  ('Azul'),
  ('Amarillo');

INSERT INTO
  `talle` (talle)
VALUES
  ('XL'),
  ('L'),
  ('M'),
  ('S');

INSERT INTO
  `usuario`
VALUES
  ('juan_cruzg','9893f9b90ebd9e22f0eba9a8af67d424','Juan Cruz','Grasso',1,1,NULL,NULL,1),
  ('usuarionormal','25d55ad283aa400af464c76d713c07ad','Usuario','Normal',1,2,NULL,NULL,1);

INSERT INTO
  `tipoPago` (id, tipoPago)
VALUES
  (1,'Efectivo'),
  (2,'Cuenta corriente'),
  (3,'Tarjeta'),
  (4,'Lleva a probar');

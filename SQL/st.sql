use db_tienda;

DELIMITER //
create procedure agregarCliente (
in _nombre varchar(30),
in _apellido varchar(30),
in _usuario varchar(30),
in _direccion varchar(30),
in _telefono int, 
in _estado boolean)
begin
insert into tbl_cliente(nombre, apellido, usuario,direccion, telefono, estado_activo)
values (_nombre, _apellido, _usuario,_direccion, _telefono, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarCliente (
in id int,
in _nombre varchar(30),
in _apellido varchar(30),
in _usuario varchar(30),
in _direccion varchar(30),
in _telefono int, 
in _estado boolean)
begin
update tbl_cliente
set nombre = _nombre, apellido = _apellido, usuario = _usuario, direccion = _direccion, telefono = _telefono, estado_activo = _estado
where id_cliente = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarCliente (in id int)
begin
update tbl_cliente
set estado_activo = false
where id_cliente = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarUsuario (
_id_cliente int,
_usuario varchar (30),
_contra varchar(20),
_rol boolean,
_estado boolean)
begin
insert into tbl_usuario(id_cliente, usuario, password_usuario, rol, estado_activo)
values (_id_cliente, _usuario, _contra, _rol, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarUsuario (
id int,
_id_cliente int,
_usuario varchar (30),
_contra varchar(20),
_rol boolean,
_estado boolean)
begin
update tbl_usuario
set id_cliente = _id_cliente, usuario = _usuario, password_usuario = _contra, rol = _rol, estado_activo = _estado
where id_usuario = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarUsuario (in id int)
begin
update tbl_usuario
set estado_activo = false
where id_usuario = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarProveedor (
in _proveedor varchar(50),
in _estado boolean)
begin
insert into tbl_proveedor(proveedor, estado_activo)
values (_proveedor, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarProveedor (
in id int,
in _proveedor varchar(50),
in _estado boolean)
begin
update tbl_proveedor
set proveedor = _proveedor, estado_activo = _estado
where id_proveedor = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarProveedor (in id int)
begin
update tbl_proveedor
set estado_activo = false
where id_proveedor = id;
end //
DELIMITER ;

DELIMITER  //
create procedure agregarProducto (
in _id_proveedor int,
in _producto varchar(50),
in _precio int,
in _estado boolean)
begin
insert into tbl_producto(id_proveedor, producto, precio, estado_activo)
values (_id_proveedor, _producto, _precio, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarProducto (
in id int,
in _id_proveedor int,
in _producto varchar(50),
in _precio int,
in _estado boolean)
begin
update tbl_producto
set id_proveedor = _id_proveedor, producto = _producto, precio = _precio, estado_activo = _estado
where id_producto = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarProducto (in id int)
begin
update tbl_producto
set estado_activo = false
where id_producto = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarVenta(
in _id_cliente int,
in _id_producto int,
in _cantidad int,
in _fecha date,
in _estado boolean)
begin
insert into tbl_venta(id_cliente, id_producto,cantidad, fecha_de_venta, estado_activo)
values (_id_cliente, _id_producto ,_cantidad, _fecha, _estado);
end //
DELIMITER ;
call agregarVenta(1,1,2,'2022-09-30',true);

DELIMITER //
create procedure actualizarVenta(
in id int,
in _id_cliente int,
in _id_producto int,
in _cantidad int,
in _fecha date,
in _estado boolean)
begin
update tbl_venta
set id_cliente = _id_cliente, id_producto = _id_producto ,cantidad = _cantidad, fecha_de_venta = _fecha, estado_activo = _estado
where id_venta = id;
end //
DELIMITER ;

DELIMITER // 
create procedure eliminarVenta(in id int)
begin
update tbl_venta
set estado_activo = false
where id_venta = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarCompra (
_id_producto int,
_cantidad int,
_fecha date,
_estado boolean)
begin 
insert into tbl_compra(id_producto,fecha_de_compra, cantidad ,estado_activo)
values (_id_producto,_fecha, _cantidad,_estado);
end //
DELIMITER //

DELIMITER //
create procedure actualizarCompra(
id int,
_id_producto int,
_cantidad int,
_fecha date,
_estado boolean)
begin
update tbl_compra
set id_producto = _id_producto ,fecha_de_compra = _fecha, cantidad = _cantidad ,estado_activo = _estado
where id_compra = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarCompra(in id int)
begin
update tbl_compra
set estado_activo = false
where id_compra = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarDetalle_Compra(
in _id_compra int,
in _id_producto int,
in _cantidad int,
in _estado boolean)
begin
insert into tbl_detalle_compra(id_compra, id_producto, cantidad, estado_activo)
values(_id_compra, _id_producto, _cantidad, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarDetalle_Compra(
in id int,
in _id_compra int,
in _id_producto int,
in _cantidad int,
in _estado boolean)
begin
update tbl_detalle_compra
set id_compra = _id_compra, id_producto = _id_producto, cantidad = _cantidad, estado_activo = _estado
where id_detalle_compra = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarDetalle_Compra(in id int)
begin
update tbl_detalle_compra
set estado_activo = false
where id_detalle_compra = id;
end //
DELIMITER ;

DELIMITER //
create procedure agregarDetalle_Venta(
in _id_venta int,
in _id_producto int,
in _cantidad int,
in _estado boolean)
begin
insert into tbl_detalle_venta(id_venta, id_producto, cantidad, estado_activo)
values(_id_venta, _id_producto, _cantidad, _estado);
end //
DELIMITER ;

DELIMITER //
create procedure actualizarDetalle_Venta(
in id int,
in _id_venta int,
in _id_producto int,
in _cantidad int,
in _estado boolean)
begin
update tbl_detalle_venta
set id_venta = _id_venta, id_producto = _id_producto, cantidad = _cantidad, estado_activo = _estado
where id_detalle_venta = id;
end //
DELIMITER ;

DELIMITER //
create procedure eliminarDetalle_Venta(in id int)
begin
update tbl_detalle_venta
set estado_activo = false
where id_detalle_venta = id;
end //
DELIMITER ;
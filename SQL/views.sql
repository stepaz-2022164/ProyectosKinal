use db_tienda;

create view vistaClientes as
select * from tbl_cliente
where tbl_cliente.id_cliente;

create view vistaProductos as
select * from tbl_cuenta
where tbl_producto.id_producto;

create view vistaUsuarios as
select * from tbl_usuario
where tbl_usuario.id_usuario;

create view vistaProveedores as
select * from tbl_proveedor
where tbl_proveedor.id_proveedor;

create view vistaCompra as
select * from tbl_compras
where tbl_pago.id_pago;

create view vistaDetalle_Compra as
select * from tbl_detalle_cliente_cuenta
where tbl_detalle_cliente_cuenta.id_detalle_cliente_cuenta;



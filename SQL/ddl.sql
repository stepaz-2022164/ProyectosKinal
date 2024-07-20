drop database if exists db_tienda;
create database db_tienda;
use db_tienda;

create table if not exists tbl_cliente(
id_cliente int not null auto_increment,
primary key(id_cliente),
unique(id_cliente),
nombre varchar(60),
apellido varchar(60),
usuario varchar(30),
direccion varchar(60),
telefono int,
estado_activo boolean
);

create table if not exists tbl_usuario(
id_usuario int not null auto_increment,
primary key(id_usuario),
unique(id_usuario),
id_cliente int not null,
usuario varchar(30),
password_usuario varchar(20),
rol boolean,
estado_activo boolean,
foreign key(id_cliente) references tbl_cliente(id_cliente)
);

create table if not exists tbl_proveedor(
id_proveedor int not null auto_increment,
primary key(id_proveedor),
unique(id_proveedor),
proveedor varchar(50),
estado_activo boolean
);

create table if not exists tbl_producto(
id_producto int not null auto_increment,
id_proveedor int not null,
primary key(id_producto),
unique(id_producto),
producto varchar(50),
precio int,
estado_activo boolean,
foreign key(id_proveedor) references tbl_proveedor(id_proveedor)
); 

create table if not exists tbl_venta(
id_venta int not null auto_increment,
primary key(id_venta),
unique(id_venta),
id_cliente int not null,
id_producto int not null,
cantidad int,
fecha_de_venta date,
estado_activo boolean,
foreign key(id_cliente) references tbl_cliente(id_cliente)
);

create table if not exists tbl_compra(
id_compra int not null auto_increment,
id_producto int not null,
primary key(id_compra),
unique(id_compra),
cantidad int,
fecha_de_compra date,
estado_activo boolean
);

create table if not exists tbl_detalle_compra(
id_detalle_compra int not null auto_increment,
primary key(id_detalle_compra),
unique(id_detalle_compra),
id_compra int not null,
id_producto int not null,
cantidad int,
estado_activo boolean,
foreign key(id_compra) references tbl_compra(id_compra),
foreign key(id_producto) references tbl_producto(id_producto)
);

create table if not exists tbl_detalle_venta(
id_detalle_venta int not null auto_increment,
primary key(id_detalle_venta),
unique(id_detalle_venta),
id_venta int not null,
id_producto int not null,
cantidad int,
estado_activo boolean,
foreign key(id_venta) references tbl_venta(id_venta),
foreign key(id_producto) references tbl_producto(id_producto)
);

SET GLOBAL time_zone ='-5:00';
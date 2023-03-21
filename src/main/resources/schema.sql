drop table if exists cliente;
CREATE TABLE cliente(
  id bigint auto_increment,
  nombres varchar(60),
  apellidos varchar(100),
  email varchar(45),
  dni char(8),
  fecha_creacion date,
  fecha_nacimiento date
)
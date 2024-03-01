-- TODO Task 3
drop database if exists ecommerce;

create database ecommerce;

use ecommerce;

create table order (

    name varchar(3) not null,
    address varchar(8) not null,
    priority boolean not null,
    comments varchar(128),
    cart,

    constraint primary key(name)
);

grant all privileges on ecommerce.* to 'fred'@'%';

flush privileges;

-- TODO Task 3
drop database if exists ecommerce;

create database ecommerce;

use ecommerce;

create table orders (

    id char(26) not null,
    date timestamp not null,
    name varchar(3) not null,
    address varchar(128) not null,
    priority boolean not null,
    comments varchar(128),

    primary key(id)
);

create table cart (

    id char(26) not null,
    productId char(8) not null,
    name varchar(128) not null,
    quantity int not null,
    price float,

    primary key(productId),
    constraint fk_id foreign key(id) references orders(id)

);

grant all privileges on ecommerce.* to 'fred'@'%';

flush privileges;

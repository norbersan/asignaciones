--liquibase formatted sql

--changeset norberto:01
create table if not exists dummy
(
    id serial primary key not null,
    text varchar(255)
);

create table if not exists assignee
(
    id serial primary key not null,
    name varchar(50),
    surname varchar(50),
    phone varchar(50),
    nick varchar(50),
    notes varchar(1024)
);

create table if not exists assignment
(
    id serial primary key not null,
    name varchar(255),
    description varchar(255),
    starts varchar(50),
    ends varchar(50),
    notes varchar(1024)
);

--liquibase formatted sql

--changeset norberto:01
create table if not exists dummy
(
    id serial primary key not null,
    text varchar(255)
)
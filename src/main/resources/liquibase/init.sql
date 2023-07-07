--liquibase formatted sql

--changeset norberto:01
create table if not exists test
(
    id int not null primary key,
    text varchar(255)
)
--liquibase formatted sql
--changeset lidij:drop_table_students
drop table if exists students CASCADE;
--changeset lidij:create_table_students
create table students (
   id serial primary key,
   name varchar(30) not null unique,
   age integer not null
);

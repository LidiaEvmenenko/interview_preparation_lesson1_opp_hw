--liquibase formatted sql
--changeset lidij:add_data_roles_1
insert into students (name, age) values('student1', 18),
('student2', 19),
('student3', 17),
('student4', 20);

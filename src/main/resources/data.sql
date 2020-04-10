/*category*/
insert into category(id,name) values (1,'IT'),(2,'Gastronomia'),(3,'Ocio&Entretenimiento'),(4,'Arte'),(5,'Cultura'),(6,'Finanzas'),(7,'Otros');
/*admin*/
insert into users (id,active,address,email,name,password,telephone,username) value (1,1,'calle false 123','admin@idea.com','admin','$2a$10$F3bzI8isNWOgH5/xuZuxvO0aZm3ihh1S/AtOr9e4OWhkiSQB8Xq1q','696969696','admin');
insert into roles (id,name,user_id) values (1,"ROLE_ADMIN", 1);
delete from Noticia;
insert into Noticia (titulo) values ('Noticia uno peli');
insert into Noticia (titulo) values ('Noticia dos peli');
insert into Noticia (titulo) values ('Noticia tres peli');

delete from Users;
insert into Users (username, password, enabled) values ('admin', '$2a$04$IwgQUlTIXUL.RithzymDju/r1jokl0Lfu4Wv.IuyFHY2IhJpC19Oi', true);

delete from Authorities;
insert into Authorities (username, authority) values ('admin', 'ADMIN');
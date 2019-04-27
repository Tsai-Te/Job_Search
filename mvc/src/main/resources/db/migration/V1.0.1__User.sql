CREATE SEQUENCE user_id_seq;

create table users (

 id bigint not null DEFAULT NEXTVAL('user_id_seq'),

 first_name varchar(255),

 last_name varchar(255) not NULL,

 primary key (id)

);

alter table users add username varchar(255) not null;

alter table users add email varchar(255) not null;

alter table users add password varchar(255) not null;

alter table users add date_of_birth date not null;

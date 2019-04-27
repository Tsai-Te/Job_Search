CREATE SEQUENCE region_id_seq;

create table regions (

 id bigint not null DEFAULT NEXTVAL('region_id_seq'),

 state varchar(255),

 city varchar(255),

 zip_code int,

 primary key (id)

);
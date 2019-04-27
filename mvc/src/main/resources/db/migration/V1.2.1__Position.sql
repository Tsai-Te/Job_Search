CREATE SEQUENCE position_id_seq;

create table positions (

 id bigint not null DEFAULT NEXTVAL('position_id_seq'),

 auditor varchar(255),

 engineer varchar(255),

 manager varchar(255),

 primary key (id)

);

create sequence authority_id_seq;

create table authorities(
  id bigint not null default nextval('authority_id_seq'),
  authority varchar (255) not null,
  user_id bigint,
  delete_admin boolean,
  primary key (id)
);

alter sequence authority_id_seq owned by authorities.id;

alter table authorities add CONSTRAINT fk_authorities_users
  foreign key (user_id)
  references users(id)
  on delete no action
  on update no action;

CREATE SEQUENCE image_id_seq;

create table images (

 id bigint not null DEFAULT NEXTVAL('image_id_seq'),

  user_id bigint,

 primary key (id)

);

alter sequence image_id_seq owned by images.id;

ALTER TABLE images ADD CONSTRAINT fk_image_user
   FOREIGN KEY (user_id)
   REFERENCES users (id)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;
alter table images add column image_UUID varchar(255) not null unique;

alter table images add column extension varchar(255) not null;


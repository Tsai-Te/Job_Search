CREATE SEQUENCE my_job_id_seq;

create table my_jobs (

 id bigint not null DEFAULT NEXTVAL('my_job_id_seq'),

 saved_jobs varchar(255),

 primary key (id)

);

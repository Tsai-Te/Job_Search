alter table my_jobs add column user_id bigint;

ALTER TABLE my_jobs ADD CONSTRAINT fk_myJobs_user
   FOREIGN KEY (user_id)
   REFERENCES users (id)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;
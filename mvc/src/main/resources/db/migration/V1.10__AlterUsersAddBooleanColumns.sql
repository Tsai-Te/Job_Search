alter table users add column account_non_expired boolean;

alter table users add column credentials_non_expired boolean;

alter table users add column account_non_locked boolean;

alter table users add column enabled boolean;
alter table users rename column account_non_expired to account_expired;

alter table users rename column credentials_non_expired to credentials_expired;

alter table users rename column account_non_locked to account_locked;

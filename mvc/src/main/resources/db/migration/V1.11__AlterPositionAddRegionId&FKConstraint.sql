alter table positions add column region_id bigint;

alter table positions add constraint fk_positions_region
    foreign key (region_id)
    references regions (id)
    on delete no action
    on update no action;
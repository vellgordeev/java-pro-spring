create table accounts (
    id bigserial primary key,
    client_id bigint,
    balance bigint,
    created_at timestamp,
    updated_at timestamp
);

create table clients (id bigserial primary key, fio varchar(255));

insert into clients (fio) values ('A A A');

insert into accounts (client_id, balance) values (1, 1000);
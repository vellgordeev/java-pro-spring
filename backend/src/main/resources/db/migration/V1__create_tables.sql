create table clients
(
    id  bigserial primary key,
    fio varchar(255)
);

create table accounts
(
    id             bigserial primary key,
    client_id      bigint references clients(id),
    account_number varchar(16),
    balance        numeric(6, 2),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

create table transfers
(
    id                 bigserial primary key,
    source_account_id   bigint references accounts(id) on delete cascade,
    target_account_id   bigint references accounts(id) on delete cascade,
    source_client_id    bigint not null,
    target_client_id    bigint not null,
    amount              numeric(10, 2) not null,
    status              varchar(50) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);
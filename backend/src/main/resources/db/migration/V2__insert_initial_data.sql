-- Добавляем клиентов
insert into clients (fio)
values
    ('A A A'),
    ('B B B'),
    ('C C C');

-- Добавляем счета для клиента 'A A A' (ID = 1)
insert into accounts (client_id, account_number, balance, created_at, updated_at)
values
    (1, '1234123412341234', 1000, now(), now()),
    (1, '1111222233334444', 2000, now(), now()),
    (1, '5555666677778888', 500, now(), now());

-- Добавляем счета для клиента 'B B B' (ID = 2)
insert into accounts (client_id, account_number, balance, created_at, updated_at)
values
    (2, '2222333344445555', 1500, now(), now()),
    (2, '6666777788889999', 2500, now(), now());

-- Добавляем счета для клиента 'C C C' (ID = 3)
insert into accounts (client_id, account_number, balance, created_at, updated_at)
values
    (3, '3333444455556666', 1200, now(), now()),
    (3, '7777888899990000', 300, now(), now());

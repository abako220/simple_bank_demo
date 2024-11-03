create sequence transaction_id_seq start with 1000 increment by 50;

create table transaction_ledger
(
    id bigint default nextval('transaction_id_seq') not null,
    transaction_id        text not null unique,
    previous_balance       decimal(10,2) default 0.00,
    current_balance decimal(10,2) default 0.00,
    amount decimal(10,2) default 0,
    sender_account text not null,
    receiver_account text not null,
    description text default '',
    transaction_type text not null,
    transaction_status text default 'PROCESSING',
    transaction_date timestamp default current_timestamp,
    primary key (id)
);
create sequence account_id_seq start with 1000 increment by 50;

create table account
(
    id bigint default nextval('account_id_seq') not null,
    account_number        text not null unique,
    account_name        text not null,
    account_status text not null,
    account_type text not null,
    opening_amount decimal(10,2) default 0.00,
    customer_firstname text not null,
    customer_lastname text not null,
    customer_phone_number text not null unique,
    customer_email text not null unique,
    address_street_no text default null,
    address_street_name text not null,
    address_city  text not null,
    address_state  text not null,
    balance decimal(10,2) default 0.00,
    created_at timestamp without time zone default current_timestamp,
    updated_at timestamp without time zone default current_timestamp,
    constraint account_type_check check (account_type in ('current', 'savings', 'loan')),
    primary key (id)
);
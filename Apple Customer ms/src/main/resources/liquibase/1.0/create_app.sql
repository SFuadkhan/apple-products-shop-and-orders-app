create table apple_orders
(
    id serial primary key,
    device       varchar   not null,
    model        varchar   not null,
    name         varchar   not null,
    surname      varchar   not null,
    created_at   timestamp not null,
    updated_at   timestamp not null,
    order_status varchar   not null,
    customer_id integer not null
);

create table apple_customers
(
    id integer  primary key,
    name         varchar   not null,
    surname      varchar   not null,
    orders integer not null default 0,
    purchases integer not null default 0
);



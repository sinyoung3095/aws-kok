create type request_status as enum('await','accept','reject');

create type status as enum('active', 'inactive');

create type role as enum('member', 'admin', 'company');

create table tbl_user (
    id bigint generated always as identity primary key,
    user_name varchar(255) not null,
    user_phone varchar(255) not null,
    user_email varchar(255) not null unique,
    user_password varchar(255),
    user_role role not null,
    user_status status default 'active' not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

select * from tbl_user;

insert into tbl_user(user_name, user_phone, user_email, user_password, user_role) values ('테스트2', '01011111112', 'test2@gmail.com', '1234', 'company');
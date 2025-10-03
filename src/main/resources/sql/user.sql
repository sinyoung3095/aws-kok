create type request_status as enum('await','accept','reject');

create type status as enum('active', 'inactive');

create type role as enum('member', 'admin', 'company');

create table tbl_user (
    id bigint generated always as identity primary key,
    user_name varchar(255) not null,
    user_phone varchar(255) not null,
    user_email varchar(255) unique,
    user_password varchar(255),
    user_role role not null,
    sns_email varchar(255),
    user_status status default 'active' not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);
alter table tbl_user add sns_email varchar(255);
alter table tbl_user alter column user_email DROP not null;
-- select * from tbl_user;
--
-- insert into tbl_user(user_name, user_phone, user_email, user_password, user_role) values ('테스트', '01011111111', 'test1@gmail.com', '1234', 'company');

select * from tbl_user;



insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('홍사장', '010-1111-1111', 'hong@example.com', '1234', 'company'),
    ('김사장', '010-2222-2222', 'kim@example.com', '1234', 'company'),
    ('이사장', '010-3333-3333', 'lee@example.com', '1234', 'company'),
    ('박사장', '010-4444-4444', 'park@example.com', '1234', 'company'),
    ('최사장', '010-5555-5555', 'choi@example.com', '1234', 'company');

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('차은우', '010-1111-2222', 'ooo@example.com', '1234', 'member'),
    ('박보검', '010-2222-3333', 'gum@example.com', '1234', 'member'),
    ('서강준', '010-3333-4444', 'jang@example.com', '1234', 'member'),
    ('뷔', '010-4444-5555', 'vvv@example.com', '1234', 'member'),
    ('변우석', '010-5555-6666', 'suck@example.com', '1234', 'member');

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('가가가', '010-1111-2223', 'oooaa@example.com', '1234', 'member'),
    ('나나나', '010-2222-3334', 'gumbb@example.com', '1234', 'member'),
    ('다다다', '010-3333-4445', 'jangc@example.com', '1234', 'member'),
    ('라라라', '010-4444-5556', 'vvvdd@example.com', '1234', 'member'),
    ('마마마', '010-5555-6667', 'sucke@example.com', '1234', 'member');

select * from tbl_user;

delete  FROM tbl_user
where id =2;




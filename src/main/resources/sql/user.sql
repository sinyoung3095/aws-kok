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

select * from tbl_user;
--
-- insert into tbl_user(user_name, user_phone, user_email, user_password, user_role) values ('테스트', '01011111111', 'test1@gmail.com', '1234', 'company');

select * from tbl_user;

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('관리자', '010-0101-0202', 'admin@example.com', '1234', 'admin');

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

select * from tbl_user;

delete  FROM tbl_user
where id =2;




INSERT INTO tbl_user (user_name, user_phone, user_email, user_password, user_role)
VALUES
    ('정해인', '010-6666-7777', 'hane@example.com', '1234', 'member'),
    ('이도현', '010-7777-8888', 'dohyun@example.com', '1234', 'member'),
    ('송강', '010-8888-9999', 'song@example.com', '1234', 'member'),
    ('남주혁', '010-9999-0000', 'nam@example.com', '1234', 'member'),
    ('유연석', '010-1010-2020', 'yeon@example.com', '1234', 'member'),
    ('김수현', '010-2020-3030', 'soo@example.com', '1234', 'member'),
    ('이민호', '010-3030-4040', 'minho@example.com', '1234', 'member'),
    ('박서준', '010-4040-5050', 'seo@example.com', '1234', 'member'),
    ('공유', '010-5050-6060', 'gong@example.com', '1234', 'member'),
    ('현빈', '010-6060-7070', 'bin@example.com', '1234', 'member'),
    ('이종석', '010-7070-8080', 'jong@example.com', '1234', 'member'),
    ('조인성', '010-8080-9090', 'insung@example.com', '1234', 'member'),
    ('강동원', '010-9090-1010', 'dongwon@example.com', '1234', 'member'),
    ('하정우', '010-1011-2022', 'jungwoo@example.com', '1234', 'member'),
    ('류준열', '010-2022-3033', 'junyeol@example.com', '1234', 'member'),
    ('유아인', '010-3033-4044', 'yain@example.com', '1234', 'member');


INSERT INTO tbl_user (user_name, user_phone, user_email, user_password, user_role)
VALUES
    ('정사장', '010-6666-1111', 'jung@example.com', '1234', 'company'),
    ('윤사장', '010-7777-2222', 'yoon@example.com', '1234', 'company'),
    ('안사장', '010-8888-3333', 'ahn@example.com', '1234', 'company'),
    ('서사장', '010-9999-4444', 'seo2@example.com', '1234', 'company'),
    ('조사장', '010-1010-5555', 'jo@example.com', '1234', 'company'),
    ('남사장', '010-2020-6666', 'nam2@example.com', '1234', 'company'),
    ('노사장', '010-3030-7777', 'noh@example.com', '1234', 'company'),
    ('문사장', '010-4040-8888', 'moon@example.com', '1234', 'company'),
    ('심사장', '010-5050-9999', 'shim@example.com', '1234', 'company'),
    ('배사장', '010-6060-1110', 'bae@example.com', '1234', 'company'),
    ('한사장', '010-7070-2220', 'han@example.com', '1234', 'company'),
    ('장사장', '010-8080-3330', 'jang2@example.com', '1234', 'company'),
    ('임사장', '010-9090-4440', 'lim@example.com', '1234', 'company'),
    ('권사장', '010-1112-5550', 'kwon@example.com', '1234', 'company'),
    ('하사장', '010-2223-6660', 'ha@example.com', '1234', 'company'),
    ('신사장', '010-3334-7770', 'shin@example.com', '1234', 'company');

select * from tbl_user;

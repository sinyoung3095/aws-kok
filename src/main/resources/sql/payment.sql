CREATE TABLE tbl_payment (
    id bigint generated always as identity primary key,
    payment_price bigint not null,
    payment_status request_status not null default 'await',
    payment_paid_datetime timestamp,
    advertisement_id bigint,
    request_experience_id bigint,
    user_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_payment_advertisement foreign key(advertisement_id)
        references tbl_advertisement(id),
    constraint fk_payment_request_experience foreign key(request_experience_id)
        references tbl_request_experience(id),
    constraint fk_payment_user foreign key(user_id)
        references tbl_user(id)
);

-- 결제 상태 컬럼 추가
ALTER TABLE tbl_payment
    ADD COLUMN payment_status request_status not null default 'await';

-- 실제 결제된 시간 컬럼 추가
ALTER TABLE tbl_payment
    ADD COLUMN payment_paid_datetime timestamp;

select * from tbl_payment;

select * from tbl_user;
select * from tbl_member;
select * from tbl_company;
select * from tbl_payment_user order by id desc;
select * from tbl_payment order by id desc;
select * from tbl_experience_notice order by id desc;
select * from tbl_request_experience order by id desc;

insert into tbl_payment (payment_price, payment_paid_datetime, request_experience_id, user_id)
values ('5000', '2025-10-09 15:12:47.797648', '20', '16');

insert into tbl_payment_user (payment_id, user_id, request_experience_id)
values ('38', '8', '30'),
       ('39', '9', '31'),
       ('40', '10', '32'),
       ('41', '17', '33'),
       ('42', '18', '34'),
       ('43', '6', '35'),
       ('44', '7', '36'),
       ('45', '8', '37'),
       ('46', '9', '38'),
       ('47', '10', '39'),
       ('48', '17', '40'),
       ('49', '18', '41'),
       ('50', '19', '42'),
       ('51', '20', '43'),
       ('52', '21', '44'),
       ('53', '22', '45'),
       ('54', '6', '46'),
       ('55', '6', '47'),
       ('56', '7', '48'),
       ('57', '8', '49'),
       ('58', '9', '50');

alter table tbl_payment
    drop payment_status;

alter table tbl_payment
    add payment_status status not null default 'active';
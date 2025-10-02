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

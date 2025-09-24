CREATE TABLE tbl_payment (
    id bigint generated always as identity primary key,
    payment_price bigint not null,
    --     payment_status enum('success', 'refund', 'fail') not null default 'success',
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

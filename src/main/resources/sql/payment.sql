create table tbl_payment
(
    id                    bigint generated always as identity
        primary key,
    payment_price         bigint                                         not null,
    created_datetime      timestamp      default now(),
    updated_datetime      timestamp      default now(),
    advertisement_id      bigint
        constraint fk_payment_advertisement
            references tbl_advertisement,
    request_experience_id bigint
        constraint fk_payment_request_experience
            references tbl_request_experience,
    user_id               bigint                                         not null
        constraint fk_payment_user
            references tbl_user,
    payment_status        request_status default 'await'::request_status not null,
    payment_paid_datetime timestamp
);
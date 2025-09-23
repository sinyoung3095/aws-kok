CREATE TABLE tbl_advertisement (
    id bigint generated always as identity primary key,
    advertisement_main_text varchar(255) not null,
    advertisement_sub_text varchar(255) not null,
    advertisement_status request_status default 'await' not null,
    --     start_advertise_datetime timestamp default now(),
    --     end_advertise_datetime timestamp default now(),
    start_advertise_datetime date not null,
    end_advertise_datetime date not null,
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_advertisement_company foreign key(company_id)
        references tbl_company(user_id)
);

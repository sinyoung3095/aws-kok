CREATE TABLE tbl_company_scale_category (
    id bigint generated always as identity primary key,
    company_scale_name varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

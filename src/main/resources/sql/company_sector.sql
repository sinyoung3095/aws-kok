CREATE TABLE tbl_company_sector (
    id bigint generated always as identity primary key,
    company_sector_name varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

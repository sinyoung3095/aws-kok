CREATE TABLE tbl_job_category (
    id bigint generated always as identity primary key,
    job_name varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

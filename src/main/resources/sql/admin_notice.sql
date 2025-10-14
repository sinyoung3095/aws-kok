CREATE TABLE tbl_admin_notice (
    id   bigint generated always as identity primary key,
    admin_notice_title   varchar(255) not null,
    admin_notice_content   varchar(255) not null,
    notice_status status default 'active' not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

select * from tbl_admin_notice order by id;
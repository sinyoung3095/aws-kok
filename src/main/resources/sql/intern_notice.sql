create table tbl_intern_notice (
    id bigint generated always as identity primary key,
    intern_notice_title varchar(255) not null,
    intern_notice_subtitle varchar(255) not null,
    intern_notice_introduce_job varchar(255) not null,
    intern_notice_major_work varchar(255) not null,
    intern_notice_notes varchar(255) not null,
    intern_notice_status status not null default 'active',
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_intern_notice_company foreign key(company_id)
        references tbl_company(user_id)
);

alter table tbl_intern_notice
    alter column intern_notice_status drop default;

alter table tbl_intern_notice
    alter column intern_notice_status type text
        using intern_notice_status::text;

alter table tbl_intern_notice
    alter column intern_notice_status type status
        using intern_notice_status::status;

alter table tbl_intern_notice
    alter column intern_notice_status set default 'active',
    alter column intern_notice_status set not null;

alter table tbl_intern_notice
    add column intern_request_status request_status default 'await' not null;
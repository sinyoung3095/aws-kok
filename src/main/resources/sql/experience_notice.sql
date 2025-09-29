CREATE TABLE tbl_experience_notice (
    id   bigint generated always as identity primary key,
    experience_notice_title   varchar(255) not null,
    experience_notice_subtitle   varchar(255) not null,
    experience_notice_introduce_job   varchar(255) not null,
    experience_notice_etc   varchar(255) not null,
    --     start_experience_date   varchar(255) not null,
    --     end_experience_date   varchar(255) not null,
    experience_start_date date not null,
    experience_end_date date not null,
--     experience_notice_notes   varchar(255) not null,
--     experience_notice_status  request_status default 'await' not null,
    experience_notice_status  status default 'inactive' not null,
    experience_request_status  request_status default 'await' not null,
    company_id   bigint not null,
    created_datetime   timestamp default now(),
    updated_datetime   timestamp default now(),
    constraint fk_experience_notice_company foreign key(company_id)
        references tbl_company(user_id)
);

-- alter table tbl_experience_notice drop experience_notice_notes;

-- 순서대로 실행해야함
alter table tbl_experience_notice
    alter column experience_notice_status drop default;

alter table tbl_experience_notice
    alter column experience_notice_status type text
        using experience_notice_status::text;

alter table tbl_experience_notice
    alter column experience_notice_status type status
        using experience_notice_status::status;

alter table tbl_experience_notice
    alter column experience_notice_status set default 'inactive',
    alter column experience_notice_status set not null;

alter table tbl_experience_notice
    add column experience_request_status request_status default 'await' not null;

select * from tbl_experience_notice;

-- insert into tbl_experience_notice(experience_notice_title, experience_notice_subtitle, experience_notice_introduce_job, experience_notice_etc, experience_start_date, experience_end_date, company_id, experience_notice_status)
-- values ('공고 제목1', '공고 부제목1', '직무 소개1', '참고사항1', '2025-09-25', '2025-09-26', 1, 'accept');

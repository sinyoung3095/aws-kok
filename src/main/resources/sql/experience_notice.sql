CREATE TABLE tbl_experience_notice (
    id bigint generated always as identity primary key,
    experience_notice_title varchar(255) not null,
    experience_notice_subtitle varchar(255) not null,
    experience_notice_introduce_job varchar(255) not null,
    experience_notice_etc varchar(255) not null,
    experience_main_tasks varchar(255) not null,
    experience_start_date date not null,
    experience_end_date date not null,
    experience_notice_start_date date not null,
    experience_notice_end_date date not null,
    experience_notice_status status default 'active' not null,
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_experience_notice_company foreign key(company_id)
        references tbl_company(user_id)
);

-- 컬럼 삭제
alter table tbl_experience_notice drop experience_notice_notes;

-- 주요업무 컬럼 추가
alter table tbl_experience_notice
    add column experience_main_tasks varchar(255) not null;

-- 게시시간(시작) 컬럼 추가
alter table tbl_experience_notice
    add column experience_notice_start_date date not null;

-- 게시시간(끝) 컬럼 추가
alter table tbl_experience_notice
    add column experience_notice_end_date date not null;


--
-- 선택사항1 - 이미 데이터를 넣은 사람이면 not null을 빼고 진행해야함(선택사항1을 했으면 2는 반드시 진행해야함)
alter table tbl_experience_notice
    alter column experience_notice_status drop not null;

-- 선택사항2 -
update tbl_experience_notice
set experience_notice_status = 'active'
where experience_notice_status is null;

select * from tbl_experience_notice;
CREATE TABLE tbl_advertisement (
    id bigint generated always as identity primary key,
    advertisement_main_text varchar(255) not null,
    advertisement_sub_text varchar(255) not null,
    advertisement_status request_status not null default 'await',
    advertisement_request_status request_status not null default 'await',
    advertise_start_datetime date not null,
    advertise_end_datetime date not null,
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_advertisement_company foreign key(company_id)
        references tbl_company(user_id)
);

select * from tbl_advertisement;

-- 컬럼명 수정
ALTER TABLE tbl_advertisement RENAME COLUMN start_advertise_datetime TO advertise_start_datetime;
ALTER TABLE tbl_advertisement RENAME COLUMN end_advertise_datetime TO advertise_end_datetime;

-- 승인 컬럼 추가
alter table tbl_advertisement add advertisement_request_status request_status not null default 'await';

insert into tbl_advertisement (advertisement_main_text, advertisement_sub_text, advertise_start_datetime, advertise_end_datetime, company_id)
values ('광고 제목06', '광고 부제목06', '2025-10-06', '2025-10-10', 10);

select * from tbl_advertisement order by id;
update tbl_advertisement set advertisement_status='accept'
where id>1;
select * from tbl_user;
select * from tbl_company;
CREATE TABLE tbl_advertisement (
    id bigint generated always as identity primary key,
    advertisement_main_text varchar(255) not null,
    advertisement_sub_text varchar(255) not null,
    advertisement_status status not null default 'active',
    advertisement_request_status request_status not null default 'await',
    advertise_start_datetime date not null,
    advertise_end_datetime date not null,
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_advertisement_company foreign key(company_id)
        references tbl_company(user_id)
);

update tbl_advertisement
set advertisement_status='active'
where id > 0;

select * from tbl_advertisement;


-- 컬럼명 수정
ALTER TABLE tbl_advertisement RENAME COLUMN start_advertise_datetime TO advertise_start_datetime;
ALTER TABLE tbl_advertisement RENAME COLUMN end_advertise_datetime TO advertise_end_datetime;

-- 승인 컬럼 추가
alter table tbl_advertisement add advertisement_request_status request_status not null default 'await';

-- 251006 이후 수정됨 - advertisement_status active, inactive로 수정
ALTER TABLE tbl_advertisement
    ALTER COLUMN advertisement_status DROP DEFAULT;

ALTER TABLE tbl_advertisement
    ALTER COLUMN advertisement_status TYPE status
        USING CASE
                  WHEN advertisement_status = 'await' THEN 'active'::status
                  ELSE 'inactive'::status
        END;

ALTER TABLE tbl_advertisement
    ALTER COLUMN advertisement_status SET DEFAULT 'active';

UPDATE tbl_advertisement
SET advertisement_status = 'active'
WHERE advertisement_status IS NULL;


SELECT id, advertisement_status
FROM tbl_advertisement
WHERE advertisement_status IS NULL;

update tbl_advertisement
set advertisement_request_status='accept'
where id>0;
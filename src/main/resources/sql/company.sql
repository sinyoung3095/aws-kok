create table tbl_company (
    user_id bigint primary key,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url varchar(255),
    constraint fk_company_user foreign key(user_id)
        references tbl_user(id)
);

alter table tbl_company DROP company_business_number;

select * from tbl_company;


insert into tbl_company(user_id, company_business_number, company_name) values (2, '123456781', '회사명2');

insert into tbl_company (user_id, company_name, company_info, company_url)
values
    (6, '그린테크', '친환경 에너지 솔루션 기업', 'https://www.greentech.co.kr'),
    (7,  '스타푸드', '프리미엄 간편식 제조업체', 'https://www.starfood.com'),
    (8,  '디지털웨이브', 'AI 기반 소프트웨어 개발사', 'https://www.digitalwave.ai'),
    (9,  '메디헬스', '의료기기 및 헬스케어 전문기업', 'https://www.medihealth.co.kr'),
    (10,  '에코빌더스', '스마트 건축·인테리어 회사', 'https://www.ecobuilders.com');

-- select * from tbl_company;
--
-- insert into tbl_company(user_id, company_business_number, company_name) values (1, '123456789', '회사명1');


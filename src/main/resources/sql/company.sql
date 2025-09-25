create table tbl_company (
    user_id bigint primary key,
    company_business_number varchar(255) not null unique,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url varchar(255),
    constraint fk_company_user foreign key(user_id)
        references tbl_user(id)
);

select * from tbl_company;

insert into tbl_company (user_id, company_business_number, company_name, company_info, company_url)
values
    (1, '123-45-67890', '그린테크', '친환경 에너지 솔루션 기업', 'https://www.greentech.co.kr'),
    (2, '987-65-43210', '스타푸드', '프리미엄 간편식 제조업체', 'https://www.starfood.com'),
    (3, '456-12-78901', '디지털웨이브', 'AI 기반 소프트웨어 개발사', 'https://www.digitalwave.ai'),
    (4, '321-54-98765', '메디헬스', '의료기기 및 헬스케어 전문기업', 'https://www.medihealth.co.kr'),
    (5, '654-32-10987', '에코빌더스', '스마트 건축·인테리어 회사', 'https://www.ecobuilders.com');

-- select * from tbl_company;
--
-- insert into tbl_company(user_id, company_business_number, company_name) values (1, '123456789', '회사명1');
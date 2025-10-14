create table tbl_company (
    user_id bigint primary key,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url varchar(255),
    constraint fk_company_user foreign key(user_id)
        references tbl_user(id)
);

alter table tbl_company DROP company_business_number;

select * from tbl_file;

select * from tbl_company;

select * from tbl_company_profile_file;
insert into tbl_company_profile_file (file_id, company_id)
values (62,10);

select * from tbl_company_background_file;
insert into tbl_company_background_file (file_id, company_id)
values (66,19);

select * from tbl_company_scale_category_bridge;
insert into tbl_company_scale_category_bridge (company_id, company_scale_category_id)
values (10,5);
delete from tbl_company_scale_category_bridge
where company_id = 10;

select * from tbl_company_sector;

select * from tbl_company_sector_category;
insert into tbl_company_sector_category (company_id, company_sector_id)
values (19,3);

select * from tbl_intern_notice;

select * from tbl_user_profile_file;
insert into tbl_user_profile_file (file_id, user_id)
values (78,38);

select * from tbl_user
where user_role = 'member';

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('5사장', '010-1111-1111', '5@example.com', '1234', 'company'),
    ('6사장', '010-2222-2222', '6@example.com', '1234', 'company'),
    ('7사장', '010-3333-3333', '7@example.com', '1234', 'company'),
    ('8사장', '010-4444-4444', '8@example.com', '1234', 'company'),
    ('9사장', '010-5555-5555', '9@example.com', '1234', 'company');


insert into tbl_company(user_id, company_business_number, company_name) values (2, '123456781', '회사명2');

insert into tbl_company (user_id, company_name, company_info)
values
    (29, '1', '1 기업소개'),
    (30,  '2', '2 기업소개'),
    (31,  '3', '3 기업소개'),
    (32,  '4', '4 기업소개'),
    (33,  '5', '5 기업소개');

insert into tbl_company (user_id, company_name)
values (1, '인텔');

delete from tbl_company where user_id = 1;

-- select * from tbl_company;
--
-- insert into tbl_company(user_id, company_business_number, company_name) values (1, '123456789', '회사명1');

INSERT INTO tbl_company (user_id, company_name, company_info, company_url)
VALUES
    (49, '블루엔진', '스마트 모빌리티 솔루션 전문기업', 'https://www.blueengine.co.kr'),
    (50, '스카이디자인', 'UX/UI 전문 디지털 디자인 스튜디오', 'https://www.skydesign.co.kr'),
    (51, '오션테크', '해양 데이터 분석 및 IoT 센서 기업', 'https://www.oceantech.io'),
    (52, '브라이트소프트', 'AI 기반 ERP 시스템 개발사', 'https://www.brightsoft.ai'),
    (53, '한빛미디어랩', '콘텐츠 크리에이티브 스튜디오', 'https://www.hanbitmedia.co.kr'),
    (54, '코스모스바이오', '생명공학·유전자 연구 기업', 'https://www.cosmosbio.com'),
    (55, '페어트레이드', '공정무역 상품 유통 및 커머스 플랫폼', 'https://www.fairtrade.co.kr'),
    (56, '넥스트에너지', '신재생에너지 발전소 운영 기업', 'https://www.nextenergy.co.kr'),
    (57, '클라우드메이트', '클라우드 인프라 및 보안 서비스 제공', 'https://www.cloudmate.io'),
    (58, '어반하우징', '스마트 주거 솔루션 및 건설 서비스 기업', 'https://www.urbanhousing.co.kr'),
    (59, '퓨처로직스', '데이터 기반 경영 컨설팅 회사', 'https://www.futurelogics.com'),
    (60, '브이커머스', '쇼핑몰 자동화 및 이커머스 플랫폼 개발사', 'https://www.vcommerce.kr'),
    (61, '하모니헬스', '통합 헬스케어 및 원격진료 플랫폼', 'https://www.harmonyhealth.co.kr'),
    (62, '에듀리버스', '에듀테크 기반 온라인 학습 플랫폼', 'https://www.eduriverse.com'),
    (63, '로지프렌즈', '스마트 물류·배송 자동화 솔루션 기업', 'https://www.logifriends.co.kr'),
    (64, '인사이트랩', '데이터 분석 및 마케팅 인사이트 전문기업', 'https://www.insightlab.kr');

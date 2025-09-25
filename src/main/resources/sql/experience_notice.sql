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
    experience_notice_status  request_status default 'await' not null,
    company_id   bigint not null,
    created_datetime   timestamp default now(),
    updated_datetime   timestamp default now(),
    constraint fk_experience_notice_company foreign key(company_id)
        references tbl_company(user_id)
);

-- alter table tbl_experience_notice drop experience_notice_notes;
--
-- select * from tbl_experience_notice;
--
-- delete from tbl_experience_notice where id=1;
--
-- insert into tbl_experience_notice(experience_notice_title, experience_notice_subtitle, experience_notice_introduce_job, experience_notice_etc, experience_start_date, experience_end_date, company_id, experience_notice_status)
-- values ('공고 제목1', '공고 부제목1', '직무 소개1', '참고사항1', '2025-09-25', '2025-09-26', 1, 'accept');

select * from tbl_experience_notice;

insert into tbl_experience_notice
(experience_notice_title, experience_notice_subtitle, experience_notice_introduce_job, experience_notice_etc,
 experience_start_date, experience_end_date, experience_notice_status, company_id)
values
    ('마케팅 인턴 모집', 'SNS 콘텐츠 제작 경험 가능', '마케팅/홍보', '인스타그램, 블로그 운영 경험 우대',
     '2025-10-01', '2025-12-31', 'await', 1),
    ('디자인 체험 인턴', 'UI/UX 실무 경험 제공', '디자인', '포토샵/피그마 가능자 환영',
     '2025-10-15', '2026-01-15', 'accept', 2),
    ('백엔드 개발 인턴', 'Spring Boot 기반 실무 경험', '백엔드 개발', 'Java, DB 경험 우대',
     '2025-11-01', '2026-02-28', 'await', 3),
    ('프론트엔드 체험 인턴', 'React 프로젝트 참여', '프론트엔드 개발', 'Javascript/React 가능자',
     '2025-10-20', '2026-01-20', 'reject', 4),
    ('영상 편집 인턴', '브랜드 홍보 영상 제작 참여', '영상 편집', '프리미어, 애프터이펙트 사용 가능자',
     '2025-11-05', '2026-03-05', 'await', 5),
    ('마케팅 체험 인턴', 'SNS 채널 운영 및 콘텐츠 제작', '마케팅/홍보', '포토샵 가능자 우대',
     '2025-10-01', '2025-12-31', 'await', 1),
    ('광고 기획 인턴', '브랜드 캠페인 서포트', '광고 기획', '엑셀 능숙자 환영',
     '2025-10-05', '2026-01-05', 'accept', 1),
    ('콘텐츠 크리에이터 인턴', '유튜브 숏폼 제작 경험', '영상 편집', '프리미어/에펙 활용 가능자',
     '2025-10-10', '2026-01-10', 'reject', 1),
    ('온라인 홍보 인턴', '인플루언서 마케팅 보조', '온라인 마케팅', 'SNS 계정 운영 경험자',
     '2025-10-15', '2026-01-15', 'await', 1),
    ('디자인 어시스턴트', 'UI/UX 시안 제작 지원', '디자인/UX', '피그마 협업 경험자',
     '2025-10-20', '2026-01-20', 'await', 1),
    ('프로젝트 매니지먼트 인턴', 'PM 보조 역할', '기획/전략', '조직적이고 꼼꼼한 성격',
     '2025-10-25', '2026-01-25', 'accept', 1),
    ('데이터 분석 인턴', 'GA 데이터 리포트 작성', '데이터/AI', 'SQL 기초 가능자',
     '2025-11-01', '2026-02-01', 'await', 1),
    ('백엔드 체험 인턴', 'Spring Boot 프로젝트 참여', '백엔드 개발', 'Java, DB 가능자',
     '2025-11-05', '2026-02-05', 'reject', 1),
    ('프론트엔드 체험 인턴', 'React UI 개발 참여', '프론트엔드 개발', 'JS/React 기초',
     '2025-11-10', '2026-02-10', 'await', 1),
    ('재무 보조 인턴', '월말 회계 결산 지원', '회계/재무', '엑셀 피벗 능숙자',
     '2025-11-15', '2026-02-15', 'accept', 1),
    ('HR 인턴', '채용 보조 및 HR 운영 지원', 'HR/인사', '꼼꼼하고 책임감 있는 성격',
     '2025-11-20', '2026-02-20', 'await', 1),
    ('경영 지원 인턴', '문서 정리 및 보고서 작성', '경영/운영', 'MS Office 가능자',
     '2025-11-25', '2026-02-25', 'await', 1);

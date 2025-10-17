create table tbl_intern_notice (
    id bigint generated always as identity primary key,
    intern_notice_title varchar(255) not null,
    intern_notice_subtitle varchar(255) not null,
    intern_notice_introduce_job varchar(255) not null,
    intern_notice_etc varchar(255) not null,
    intern_main_tasks varchar(255) not null,
    intern_notice_start_date date not null,
    intern_notice_end_date date not null,
    intern_notice_status status default 'active' not null,
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

-- 순서대로 실행
-- 주요업무 컬럼 이름 변경
alter table tbl_intern_notice
    rename column intern_notice_major_work to intern_main_tasks;

-- 게시 상태값(시작) 컬럼 추가
alter table tbl_intern_notice
    add column intern_start_date date not null;

-- 게시 상태값(완료) 컬럼 추가
alter table tbl_intern_notice
    add column intern_end_date date not null;

-- 게시시간(시작) 컬럼 추가
alter table tbl_intern_notice
    add column intern_notice_start_date date not null;

-- 게시시간(완료) 컬럼 추가
alter table tbl_intern_notice
    add column intern_notice_end_date date not null;

-- 참고사항 컬럼 추가
alter table tbl_intern_notice
    add column intern_notice_etc varchar(255) not null;

-- notice_notes 컬럼 삭제
alter table tbl_intern_notice
    drop intern_notice_notes;

select * from tbl_intern_notice;

-- 1번 회사 (그린테크) - 12개
insert into tbl_intern_notice (
    intern_notice_title, intern_notice_subtitle, intern_notice_introduce_job,
    intern_notice_etc, intern_main_tasks,
    intern_start_date, intern_end_date, intern_notice_start_date, intern_notice_end_date,
    intern_notice_status, company_id
)
values
    ('그린테크 인턴 1', '에너지 효율 프로젝트', '신재생에너지 관련 업무 지원', '환경 관련 연구 과제 참여', '데이터 분석, 보고서 작성',
     '2025-10-01', '2026-01-31', '2025-09-25', '2025-10-31', 'active', 1),
    ('그린테크 인턴 2', '태양광 모듈 개발 보조', '태양광 발전 시스템 설계 지원', '현장 테스트 참여', '시스템 모니터링',
     '2025-11-01', '2026-02-28', '2025-10-01', '2025-11-15', 'active', 1),
    ('그린테크 인턴 3', '에너지 데이터 수집', '스마트 미터링 데이터 분석', '보고서 작성 지원', '데이터 시각화',
     '2025-10-15', '2026-01-15', '2025-09-30', '2025-10-30', 'inactive', 1),
    ('그린테크 인턴 4', '풍력 발전 연구', '풍력 터빈 운영 분석', '환경 보고서 작성', '현장 조사',
     '2025-12-01', '2026-03-31', '2025-11-01', '2025-12-15', 'active', 1),
    ('그린테크 인턴 5', '환경정책 지원', '친환경 정책 조사 보조', '보고서 정리', '문헌 조사',
     '2025-10-10', '2025-12-20', '2025-09-28', '2025-10-15', 'active', 1),
    ('그린테크 인턴 6', '연구실 실험 보조', '에너지 소재 실험 참여', '기록 데이터 관리', '실험 환경 관리',
     '2025-11-10', '2026-02-10', '2025-10-15', '2025-11-10', 'inactive', 1),
    ('그린테크 인턴 7', '스마트그리드 프로젝트', '전력망 관리 시스템 보조', '프로토콜 검증', '데이터 로깅',
     '2025-09-20', '2025-12-20', '2025-09-10', '2025-09-30', 'active', 1),
    ('그린테크 인턴 8', '탄소 저감 연구', '탄소 배출량 모니터링 지원', '보고서 정리', '실험 데이터 수집',
     '2025-10-05', '2026-01-05', '2025-09-20', '2025-10-10', 'active', 1),
    ('그린테크 인턴 9', '환경기술 홍보', '블로그 콘텐츠 제작', 'SNS 운영', '기사 번역',
     '2025-10-20', '2026-01-20', '2025-10-01', '2025-10-25', 'active', 1),
    ('그린테크 인턴 10', '그린 캠페인', '대학생 캠페인 운영 보조', '행사 준비', '현장 운영',
     '2025-11-15', '2026-02-15', '2025-11-01', '2025-11-30', 'active', 1),
    ('그린테크 인턴 11', 'R&D 프로젝트 지원', '소재 분석 실험 보조', '데이터 기록', '결과 보고서 작성',
     '2025-12-05', '2026-03-05', '2025-11-20', '2025-12-10', 'inactive', 1),
    ('그린테크 인턴 12', '환경 디자인', '친환경 제품 디자인 보조', '시제품 제작 참여', 'CAD 설계',
     '2025-10-25', '2026-01-25', '2025-10-10', '2025-10-31', 'active', 1);

-- 2번 회사 (스타푸드) - 1개
insert into tbl_intern_notice (
    intern_notice_title, intern_notice_subtitle, intern_notice_introduce_job,
    intern_notice_etc, intern_main_tasks,
    intern_start_date, intern_end_date, intern_notice_start_date, intern_notice_end_date,
    intern_notice_status, company_id
)
values
    ('스타푸드 인턴', '프리미엄 간편식 연구', '레시피 개발 지원', '식품위생 관리', '조리 실습 보조',
     '2025-10-01', '2026-01-31', '2025-09-20', '2025-10-10', 'active', 2);

-- 3번 회사 (디지털웨이브) - 1개
insert into tbl_intern_notice (
    intern_notice_title, intern_notice_subtitle, intern_notice_introduce_job,
    intern_notice_etc, intern_main_tasks,
    intern_start_date, intern_end_date, intern_notice_start_date, intern_notice_end_date,
    intern_notice_status, company_id
)
values
    ('디지털웨이브 인턴', 'AI 모델 학습 데이터 구축', '데이터셋 정제 및 가공', '팀 협업 지원', 'Python 스크립트 작성',
     '2025-11-01', '2026-02-28', '2025-10-01', '2025-11-10', 'active', 3);

-- 4번 회사 (메디헬스) - 1개
insert into tbl_intern_notice (
    intern_notice_title, intern_notice_subtitle, intern_notice_introduce_job,
    intern_notice_etc, intern_main_tasks,
    intern_start_date, intern_end_date, intern_notice_start_date, intern_notice_end_date,
    intern_notice_status, company_id
)
values
    ('메디헬스 인턴', '헬스케어 데이터 분석', '의료 데이터 처리 지원', '환자 정보 관리', '데이터 전처리',
     '2025-12-01', '2026-03-01', '2025-11-01', '2025-11-30', 'inactive', 4);

-- 5번 회사 (에코빌더스) - 1개
insert into tbl_intern_notice (
    intern_notice_title, intern_notice_subtitle, intern_notice_introduce_job,
    intern_notice_etc, intern_main_tasks,
    intern_start_date, intern_end_date, intern_notice_start_date, intern_notice_end_date,
    intern_notice_status, company_id
)
values
    ('에코빌더스 인턴', '스마트 건축 인턴십', '친환경 자재 조사', '현장 안전 관리', 'CAD 도면 작성',
     '2025-10-10', '2026-01-10', '2025-09-25', '2025-10-15', 'active', 5);

-- 인턴 기한 삭제
alter table tbl_intern_notice
    drop intern_start_date;

alter table tbl_intern_notice
    drop intern_end_date;
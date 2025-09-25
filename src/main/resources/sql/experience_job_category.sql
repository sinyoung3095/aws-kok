CREATE TABLE tbl_experience_job_category (
    experience_notice_id bigint primary key,
    job_category bigint not null,
    constraint fk_experience_job_category_user foreign key(experience_notice_id)
       references tbl_experience_notice(id),
    constraint fk_user_job_category_job_category foreign key(job_category)
       references tbl_job_category(id)
);

select * from tbl_experience_job_category;

insert into tbl_experience_job_category (experience_notice_id, job_category) values
             (1, 1), -- 마케팅 인턴 모집 → 마케팅/PR
             (2, 2), -- 디자인 체험 인턴 → 디자인/UX
             (3, 3), -- 백엔드 개발 인턴 → 백엔드 개발
             (4, 4), -- 프론트엔드 체험 인턴 → 프론트엔드 개발
             (5, 5), -- 영상 편집 인턴 → 영상/미디어
             (6, 1),   -- 마케팅 체험 인턴 → 마케팅/PR
             (7, 6),   -- 광고 기획 인턴 → 광고 기획
             (8, 5),   -- 콘텐츠 크리에이터 인턴 → 영상/미디어
             (9, 1),   -- 온라인 홍보 인턴 → 마케팅/PR
             (10, 2),  -- 디자인 어시스턴트 → 디자인/UX
             (11, 7),  -- 프로젝트 매니지먼트 인턴 → 기획/전략
             (12, 8),  -- 데이터 분석 인턴 → 데이터/AI
             (13, 3),  -- 백엔드 체험 인턴 → 백엔드 개발
             (14, 4),  -- 프론트엔드 체험 인턴 → 프론트엔드 개발
             (15, 9),  -- 재무 보조 인턴 → 회계/재무
             (16, 10), -- HR 인턴 → HR/인사
             (17, 11); -- 경영 지원 인턴 → 경영/운영


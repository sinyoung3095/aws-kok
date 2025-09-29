CREATE TABLE tbl_experience_job_category (
    experience_notice_id bigint primary key,
    job_category bigint not null,
    constraint fk_experience_job_category_user foreign key(experience_notice_id)
       references tbl_experience_notice(id),
    constraint fk_user_job_category_job_category foreign key(job_category)
       references tbl_job_category(id)
);

select * from tbl_experience_job_category;

insert into tbl_experience_job_category (experience_notice_id, job_category) values-- 마케팅 인턴 모집 → 마케팅/PR
             (17, 4), -- 디자인 체험 인턴 → 디자인/UX
             (18, 5), -- 백엔드 개발 인턴 → 백엔드 개발
             (19, 1), -- 프론트엔드 체험 인턴 → 프론트엔드 개발
             (20, 2), -- 영상 편집 인턴 → 영상/미디어
             (21, 1); -- 경영 지원 인턴 → 경영/운영


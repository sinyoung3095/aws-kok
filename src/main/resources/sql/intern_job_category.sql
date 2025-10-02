CREATE TABLE tbl_intern_job_category (
    intern_notice_id bigint primary key,
    job_category bigint not null,
    constraint fk_intern_job_category_user foreign key(intern_notice_id)
       references tbl_intern_notice(id),
    constraint fk_user_job_category_job_category foreign key(job_category)
       references tbl_job_category(id)
);

select * from tbl_intern_job_category;

-- 1번 회사 (그린테크) 공고 12개 매핑
insert into tbl_intern_job_category (intern_notice_id, job_category) values
                                                                         (1, 1),   -- SW 개발
                                                                         (2, 2),   -- 데이터/AI
                                                                         (3, 3),   -- 기획/전략
                                                                         (4, 12),  -- HW 개발
                                                                         (5, 13),  -- 연구/R&D
                                                                         (6, 4),   -- 디자인/UX
                                                                         (7, 5),   -- 마케팅/PR
                                                                         (8, 18),  -- 제조/생산
                                                                         (9, 20),  -- 식품/조리
                                                                         (10, 21), -- 숙박/레저
                                                                         (11, 6),  -- 경영/운영
                                                                         (12, 7);  -- HR/인사

-- 2번 회사 (스타푸드)
insert into tbl_intern_job_category (intern_notice_id, job_category) values
    (13, 20); -- 식품/조리

-- 3번 회사 (디지털웨이브)
insert into tbl_intern_job_category (intern_notice_id, job_category) values
    (14, 2);  -- 데이터/AI

-- 4번 회사 (메디헬스)
insert into tbl_intern_job_category (intern_notice_id, job_category) values
    (15, 15); -- 의료/바이오

-- 5번 회사 (에코빌더스)
insert into tbl_intern_job_category (intern_notice_id, job_category) values
    (16, 16); -- 건설/부동산


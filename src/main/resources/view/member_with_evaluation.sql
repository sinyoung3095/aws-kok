create view view_user_with_evaluation_and_job as
select
    u.*,
    v.avg_score,
    v.member_profile_url,
    j.job_name
from tbl_user u
-- 평균 점수 조인
         left join (
    select
        m.user_id, m.member_profile_url,
        ROUND(avg(e.evaluation_avg_score)::numeric, 1) as avg_score
    from tbl_member m
             join tbl_evaluation e
                  on m.user_id = e.member_id
    group by m.user_id
) v
                   on u.id = v.user_id
-- 유저-직무 매핑 조인
         left join tbl_user_job_category ujc
                   on u.id = ujc.user_id
-- 직무 카테고리 이름 조인
         left join tbl_job_category j
                   on ujc.job_category = j.id;

select * from view_user_with_evaluation_and_job;
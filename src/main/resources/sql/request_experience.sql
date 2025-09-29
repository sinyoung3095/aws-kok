CREATE TABLE tbl_request_experience (
    id bigint generated always as identity primary key,
    request_experience_status request_status  default 'await' not null,
    experience_notice_id bigint not null,
    member_id bigint not null,
    member_alarm_setting_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_request_experience_experience_notice foreign key(experience_notice_id)
        references tbl_experience_notice(id),
    constraint fk_request_experience_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_request_experience_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id)
);

select * from tbl_request_experience;

select id, company_id, experience_notice_status, experience_request_status
from tbl_experience_notice
where company_id = 1;

select *
from tbl_request_experience
where experience_notice_id in (6,11,12,13,14,15,16);

select count(*) from tbl_request_experience;

insert into tbl_request_experience
(request_experience_status, experience_notice_id, member_id, member_alarm_setting_id)
values ('accept', 6, 9, 1);

insert into tbl_request_experience
(request_experience_status, experience_notice_id, member_id, member_alarm_setting_id)
values
    ('await', 17, 6, 1),   -- 유저 6번, 대기중
    ('await', 17, 7, 2),  -- 유저 7번, 수락됨
    ('await', 17, 8, 3),  -- 유저 8번, 거절됨
    ('await', 17, 9, 4),   -- 유저 9번, 대기중
    ('await', 17, 10, 5); -- 유저 10번, 수락됨


insert into tbl_request_experience (request_experience_status, experience_notice_id, member_id, member_alarm_setting_id)
values
    ('await', 1, 6, 1),   -- 유저 6 (차은우 팬클럽 회장), 공고 1 지원 대기중
    ('accept', 1, 7, 2),  -- 유저 7 (뮤지컬 좋아함), 공고 1 지원 수락
    ('reject', 1, 8, 3),  -- 유저 8 (여행 덕후), 공고 1 지원 거절
    ('await', 2, 9, 1),   -- 유저 9 (사진 취미), 공고 2 지원 대기중
    ('accept', 2, 10, 2), -- 유저 10 (간단 프로필 없음), 공고 2 지원 수락
    ('await', 3, 6, 3),   -- 유저 6, 공고 3 지원 대기중
    ('await', 3, 7, 1),   -- 유저 7, 공고 3 지원 대기중
    ('accept', 3, 8, 2),  -- 유저 8, 공고 3 지원 수락
    ('reject', 4, 9, 3),  -- 유저 9, 공고 4 지원 거절
    ('await', 4, 10, 1);  -- 유저 10, 공고 4 지원 대기중

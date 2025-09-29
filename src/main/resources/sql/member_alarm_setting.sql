create table tbl_member_alarm_setting (
    id bigint generated always as identity primary key,
    company_post_alarm status default 'active' not null,
    member_comment_alarm status default 'active' not null,
    member_reply_alarm status default 'active' not null,
    request_status_alarm status default 'active' not null,
    --     experience_request_status_alarm status default 'active' not null,
    company_notice_alarm status default 'active' not null,
    member_id bigint not null,
    constraint fk_member_alarm_setting_member foreign key(member_id)
        references tbl_member(user_id)
);

insert into tbl_member_alarm_setting
(company_post_alarm, member_comment_alarm, member_reply_alarm, request_status_alarm, company_notice_alarm, member_id)
values
    ('active', 'active', 'active', 'active', 'active', 6), -- 차은우 팬클럽 회장
    ('active', 'inactive', 'active', 'active', 'inactive', 7), -- 뮤지컬 좋아함
    ('inactive', 'active', 'active', 'inactive', 'active', 8), -- 여행 덕후
    ('active', 'active', 'inactive', 'active', 'active', 9), -- 사진 취미
    ('inactive', 'inactive', 'inactive', 'inactive', 'inactive', 10); -- 간단 프로필 없음

select * from tbl_member_alarm_setting;
create table tbl_member_alarm_setting (
    id bigint generated always as identity primary key,
    company_experience_notice_alarm status default 'active' not null,
    company_intern_notice_alarm status default 'active' not null,
    member_id bigint not null,
    constraint fk_member_alarm_setting_member foreign key(member_id)
        references tbl_member(user_id)
);

select * from tbl_member_alarm_setting;

insert into tbl_member_alarm_setting (member_id)
values (23);

alter table tbl_member_alarm_setting RENAME company_post_alarm TO member_post_like_alarm;
-- 2번째
alter table tbl_member_alarm_setting drop member_post_like_alarm;
alter table tbl_member_alarm_setting drop member_comment_alarm;
alter table tbl_member_alarm_setting drop member_reply_alarm;
alter table tbl_member_alarm_setting RENAME  request_status_alarm TO company_experience_notice_alarm;
alter table tbl_member_alarm_setting RENAME company_notice_alarm TO company_intern_notice_alarm;

delete from tbl_member_alarm_setting
where id>0;
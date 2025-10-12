create table tbl_member_alarm_setting (
    id bigint generated always as identity primary key,
    member_post_like_alarm status default 'active' not null,
    member_comment_alarm status default 'active' not null,
    member_reply_alarm status default 'active' not null,
    request_status_alarm status default 'active' not null,
    --     experience_request_status_alarm status default 'active' not null,
    company_notice_alarm status default 'active' not null,
    member_id bigint not null,
    constraint fk_member_alarm_setting_member foreign key(member_id)
        references tbl_member(user_id)
);

select * from tbl_member_alarm_setting;

insert into tbl_member_alarm_setting (member_id)
values (1);

alter table tbl_member_alarm_setting RENAME company_post_alarm TO member_post_like_alarm;

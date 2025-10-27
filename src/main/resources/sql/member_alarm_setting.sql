create table tbl_member_alarm_setting
(
    id                   bigint generated always as identity
        primary key,
    company_post_alarm   status default 'active'::status not null,
    member_comment_alarm status default 'active'::status not null,
    member_reply_alarm   status default 'active'::status not null,
    request_status_alarm status default 'active'::status not null,
    company_notice_alarm status default 'active'::status not null,
    member_id            bigint                          not null
        constraint fk_member_alarm_setting_member
            references tbl_member
);
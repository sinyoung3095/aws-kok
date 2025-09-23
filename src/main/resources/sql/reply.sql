CREATE TABLE tbl_reply (
    id bigint generated always as identity primary key,
    reply_content text not null,
    reply_status status default 'active' not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    member_id bigint not null,
    comment_id bigint not null,
    member_alarm_setting_id bigint not null,
    constraint fk_reply_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_reply_comment foreign key(comment_id)
        references tbl_comment(id),
    constraint fk_reply_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id)
);


CREATE TABLE tbl_member_comment_alarm (
    id bigint generated always as identity primary key,
    member_id bigint not null,
    post_id bigint not null,
    comment_id bigint not null,
    member_alarm_setting_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_member_comment_alarm_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_member_comment_alarm_post foreign key(post_id)
        references tbl_post(id),
    constraint fk_member_comment_alarm_comment foreign key(comment_id)
        references tbl_comment(id),
    constraint fk_member_comment_alarm_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id)
);


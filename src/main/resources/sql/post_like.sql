CREATE TABLE tbl_post_like (
    id bigint generated always as identity primary key,
    member_id bigint not null,
    post_id bigint not null,
    member_alarm_setting_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_post_like_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_post_like_post foreign key(post_id)
        references tbl_post(id),
    constraint fk_post_like_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id)
);

delete from tbl_post_like
where id>0;

select * from tbl_post_like;

ALTER TABLE tbl_post_like
ALTER COLUMN member_alarm_setting_id DROP NOT NULL;

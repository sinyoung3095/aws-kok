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

insert into tbl_request_experience (experience_notice_id, member_id, member_alarm_setting_id)
values (1, 1, 1);

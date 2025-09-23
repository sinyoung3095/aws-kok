CREATE TABLE tbl_company_notice_alarm (
    id bigint generated always as identity primary key,
    member_alarm_setting_id bigint not null,
    follow_id bigint not null,
    experience_notice_id bigint not null,
    intern_notice_id bigint not null,
    member_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_company_notice_alarm_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id),
    constraint fk_company_notice_alarm_follow foreign key(follow_id)
        references tbl_follow(id),
    constraint fk_company_notice_alarm_experience_notice foreign key(experience_notice_id)
        references tbl_experience_notice(id),
    constraint fk_company_notice_intern_notice foreign key(intern_notice_id)
        references tbl_intern_notice(id),
    constraint fk_company_notice_alarm_member foreign key(member_id)
        references tbl_member(user_id)
);

create table tbl_request_intern (
    id bigint generated always as identity primary key,
    request_intern_status request_status not null default 'await',
    member_id bigint not null,
    intern_notice_id bigint not null,
    member_alarm_setting_id bigint not null,
    evaluation_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_request_intern_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_request_intern_intern_notice foreign key(intern_notice_id)
        references tbl_intern_notice(id),
    constraint fk_request_intern_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id),
    constraint fk_request_intern_evaluation foreign key(evaluation_id)
        references tbl_evaluation(id)
);
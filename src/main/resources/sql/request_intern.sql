create table tbl_request_intern
(
    id                      bigint generated always as identity
        primary key,
    request_intern_status   request_status default 'await'::request_status not null,
    member_id               bigint                                         not null
        constraint fk_request_intern_member
            references tbl_member,
    intern_notice_id        bigint                                         not null
        constraint fk_request_intern_intern_notice
            references tbl_intern_notice,
    member_alarm_setting_id bigint                                         not null
        constraint fk_request_intern_member_alarm_setting
            references tbl_member_alarm_setting,
    evaluation_id           bigint                                         not null
        constraint fk_request_intern_evaluation
            references tbl_evaluation,
    created_datetime        timestamp      default now(),
    updated_datetime        timestamp      default now()
);
create table tbl_save_experience_notice
(
    member_id            bigint not null
        primary key
        constraint fk_save_experience_notice_member
            references tbl_member,
    experience_notice_id bigint not null
        constraint fk_save_experience_notice_experience_notice
            references tbl_experience_notice,
    created_datetime     timestamp default now(),
    updated_datetime     timestamp default now()
);
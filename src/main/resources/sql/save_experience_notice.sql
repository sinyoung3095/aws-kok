CREATE TABLE tbl_save_experience_notice (
    member_id bigint primary key,
    experience_notice_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_save_experience_notice_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_save_experience_notice_experience_notice foreign key(experience_notice_id)
        references tbl_experience_notice(id)
);

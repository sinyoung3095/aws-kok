CREATE TABLE tbl_save_intern_notice (
    member_id bigint primary key,
    intern_notice_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_save_intern_notice_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_save_intern_notice_intern_notice foreign key(intern_notice_id)
        references tbl_intern_notice(id)
);


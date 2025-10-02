CREATE TABLE tbl_save_intern_notice (
    member_id bigint,
    intern_notice_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint pk_save_intern_notice primary key (member_id, intern_notice_id),
    constraint fk_save_intern_notice_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_save_intern_notice_intern_notice foreign key(intern_notice_id)
        references tbl_intern_notice(id)
);

-- ALTER TABLE tbl_save_intern_notice
--     DROP CONSTRAINT tbl_save_intern_notice_pkey;
--
-- ALTER TABLE tbl_save_intern_notice
--     ADD CONSTRAINT pk_save_intern_notice
--         PRIMARY KEY (member_id, intern_notice_id);

select * from tbl_save_intern_notice;

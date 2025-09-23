CREATE TABLE tbl_member_background_file (
    file_id   bigint not null,
    --     user_id   bigint not null,
    member_id bigint not null,
    constraint fk_member_background_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_member_background_file_user foreign key(member_id)
        references tbl_member(user_id)
);

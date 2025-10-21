CREATE TABLE tbl_member_storage_file (
    file_id bigint not null,
    member_id bigint not null,
    constraint fk_member_storage_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_member_storage_file_member foreign key(member_id)
        references tbl_member(user_id)
);

select * from tbl_member_storage_file;

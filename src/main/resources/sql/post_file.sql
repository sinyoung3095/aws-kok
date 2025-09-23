CREATE TABLE tbl_post_file (
    file_id bigint not null,
    post_id bigint not null,
    constraint fk_post_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_post_file_post foreign key(post_id)
        references tbl_post(id)
);

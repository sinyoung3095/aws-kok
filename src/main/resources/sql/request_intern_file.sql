CREATE TABLE tbl_request_intern_file (
    file_id bigint not null,
    request_intern_id bigint not null,
    constraint fk_request_intern_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_request_intern_file_request_intern foreign key(request_intern_id)
        references tbl_request_intern(id)
);

CREATE TABLE tbl_request_experience_file (
    file_id bigint primary key,
    request_experience_id bigint not null,
    constraint fk_request_experience_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_request_experience_file_request_experience foreign key(request_experience_id)
        references tbl_request_experience(id)
);

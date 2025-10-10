CREATE TABLE tbl_request_intern_file (
    id bigint generated always as identity primary key,
    file_id bigint not null,
    request_intern_id bigint not null,
    constraint fk_request_intern_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_request_intern_file_request_intern foreign key(request_intern_id)
        references tbl_request_intern(id)
);

select *  from tbl_request_intern_file;

ALTER TABLE tbl_request_intern_file
DROP CONSTRAINT fk_request_intern_file_file;

alter table tbl_request_intern_file drop file_id;

alter table tbl_request_intern_file add id bigint generated always as identity primary key;

alter table tbl_request_intern_file add column file_id bigint;

alter table tbl_request_intern_file
add constraint fk_request_intern_file_file
foreign key(file_id)
references tbl_file(id);
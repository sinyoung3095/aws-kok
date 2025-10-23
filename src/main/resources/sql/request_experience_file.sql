CREATE TABLE tbl_request_experience_file (
    id bigint generated always as identity primary key,
    file_id bigint ,
    request_experience_id bigint not null,
    constraint fk_request_experience_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_request_experience_file_request_experience foreign key(request_experience_id)
        references tbl_request_experience(id)
);

select *  from tbl_request_experience_file;

ALTER TABLE tbl_request_experience_file
DROP CONSTRAINT fk_request_experience_file_file;

alter table tbl_request_experience_file drop file_id;

alter table tbl_request_experience_file add id bigint generated always as identity primary key;

alter table tbl_request_experience_file add column file_id bigint;

alter table tbl_request_experience_file
add constraint fk_request_experience_file_file
foreign key(file_id)
references tbl_file(id);

delete from tbl_request_experience_file where id>0;
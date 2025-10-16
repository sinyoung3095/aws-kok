CREATE TABLE tbl_company_profile_file (
    file_id bigint primary key,
    company_id bigint not null,
    constraint fk_company_profile_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_company_profile_file_company foreign key(company_id)
        references tbl_company(user_id)
);

select * from tbl_company_profile_file;
select * from tbl_company_background_file;
select * from tbl_file;
insert into tbl_company_profile_file (file_id, company_id)
values (8, 24);
insert into tbl_company_background_file (file_id, company_id)
values (8, 24);

select * from tbl_company;
delete from tbl_company_background_file;
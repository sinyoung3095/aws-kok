CREATE TABLE tbl_company_background_file (
    file_id bigint not null,
    company_id bigint not null,
    constraint fk_company_background_file_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_company_background_file_company foreign key(company_id)
        references tbl_company(user_id)
);

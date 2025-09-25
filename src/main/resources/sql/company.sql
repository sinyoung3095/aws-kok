create table tbl_company (
    user_id bigint primary key,
    company_business_number varchar(255) not null unique,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url varchar(255),
    constraint fk_company_user foreign key(user_id)
        references tbl_user(id)
);

select * from tbl_company;

insert into tbl_company(user_id, company_business_number, company_name) values (2, '123456781', '회사명2');
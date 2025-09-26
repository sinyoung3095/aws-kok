CREATE TABLE tbl_company_sector_category (
     company_id bigint primary key,
     company_sector_id bigint not null,
     constraint fk_company_sector_category_company foreign key(company_id)
         references tbl_company(user_id),
     constraint fk_company_sector_category_company_sector foreign key(company_sector_id)
         references tbl_company_sector(id)
);

select * from tbl_company_sector_category;

insert into tbl_company_sector_category(company_id, company_sector_id)
values (7, 1),
       (8, 5),
       (9, 2),
       (10, 5),
       (11, 4);
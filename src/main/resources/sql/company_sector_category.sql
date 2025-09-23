CREATE TABLE tbl_company_sector_category (
     company_id bigint primary key,
     company_sector_id bigint not null,
     constraint fk_company_sector_category_company foreign key(company_id)
         references tbl_company(user_id),
     constraint fk_company_sector_category_company_sector foreign key(company_sector_id)
         references tbl_company_sector(id)
);

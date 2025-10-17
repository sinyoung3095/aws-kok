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

SELECT
    c.user_id AS company_id,
    c.company_name,
    c.company_info,
    c.company_url,
    scc.scale_category_name AS company_scale_name,
    sc.sector_name AS company_sector_name
FROM tbl_company c
         LEFT JOIN tbl_company_scale_category_bridge csb
                   ON c.user_id = csb.company_id
         LEFT JOIN tbl_company_scale_category scc
                   ON csb.company_scale_category_id = scc.id
         LEFT JOIN tbl_company_sector_category csc
                   ON c.user_id = csc.company_id
         LEFT JOIN tbl_company_sector sc
                   ON csc.company_sector_id = sc.id
WHERE c.user_id = 19;

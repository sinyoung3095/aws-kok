CREATE TABLE tbl_company_scale_category_bridge (
    company_id bigint not null,
    company_scale_category_id bigint not null,
    constraint fk_company_scale_category_bridge_company foreign key(company_id)
        references tbl_company(user_id),
    constraint fk_company_scale_category_bridge_company_scale_category foreign key(company_scale_category_id)
        references tbl_company_scale_category(id)
);

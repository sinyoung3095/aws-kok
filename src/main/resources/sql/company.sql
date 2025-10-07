create table tbl_company (
    user_id bigint primary key,
    company_name varchar(255) not null,
    company_info varchar(255),
    company_url varchar(255),
    constraint fk_company_user foreign key(user_id)
        references tbl_user(id)
);

alter table tbl_company DROP company_business_number;

select * from tbl_company;
select * from tbl_user
where user_role = 'company';

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values
    ('5사장', '010-1111-1111', '5@example.com', '1234', 'company'),
    ('6사장', '010-2222-2222', '6@example.com', '1234', 'company'),
    ('7사장', '010-3333-3333', '7@example.com', '1234', 'company'),
    ('8사장', '010-4444-4444', '8@example.com', '1234', 'company'),
    ('9사장', '010-5555-5555', '9@example.com', '1234', 'company');


insert into tbl_company(user_id, company_business_number, company_name) values (2, '123456781', '회사명2');

insert into tbl_company (user_id, company_name, company_info)
values
    (29, '1', '1 기업소개'),
    (30,  '2', '2 기업소개'),
    (31,  '3', '3 기업소개'),
    (32,  '4', '4 기업소개'),
    (33,  '5', '5 기업소개');

insert into tbl_company (user_id, company_name)
values (1, '인텔');

delete from tbl_company where user_id = 1;

-- select * from tbl_company;
--
-- insert into tbl_company(user_id, company_business_number, company_name) values (1, '123456789', '회사명1');


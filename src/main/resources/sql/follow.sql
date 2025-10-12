CREATE TABLE tbl_follow (
    id bigint generated always as identity primary key,
    member_id bigint not null,
    company_id bigint not null,
    follow_status status default 'active',
    constraint fk_follow_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_follow_company foreign key(company_id)
        references tbl_company(user_id),
    constraint uq_follow_member_company unique (member_id, company_id)
);

select * from tbl_follow;
select * from tbl_member;
select * from tbl_company;
select * from tbl_user;
alter table tbl_follow
    add constraint uq_follow_member_company unique (member_id, company_id);

insert into tbl_follow (member_id, company_id)
values (5, 18);

select * from tbl_follow where member_id = 12;
SELECT c.user_id,
       c.company_name,
       c.company_info,
       c.company_url,
       COUNT(f.id) AS follow_count
FROM tbl_company c
         LEFT JOIN tbl_follow f ON c.user_id = f.company_id
WHERE f.follow_status = 'active'
GROUP BY c.user_id, c.company_name, c.company_info, c.company_url
ORDER BY follow_count DESC
LIMIT 6;
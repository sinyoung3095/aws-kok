CREATE TABLE tbl_follow (
    id bigint generated always as identity primary key,
    member_id bigint not null,
    company_id bigint not null,
    follow_status status default 'active',
    constraint fk_follow_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_follow_company foreign key(company_id)
        references tbl_company(user_id)
);

CREATE TABLE tbl_report (
    id bigint generated always as identity primary key,
    post_id bigint not null,
    member_id bigint not null,
    constraint fk_report_post foreign key(post_id)
        references tbl_post(id),
    constraint fk_report_member foreign key(member_id)
        references tbl_member(user_id)
);


select * from tbl_report;
delete from tbl_report;

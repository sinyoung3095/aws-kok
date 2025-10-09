CREATE TABLE tbl_report (
    id bigint generated always as identity primary key,
    post_id bigint not null,
    member_id bigint not null,
    constraint fk_report_post foreign key(post_id)
        references tbl_post(id),
    constraint fk_report_member foreign key(member_id)
        references tbl_member(user_id)
);

insert into tbl_report (post_id, member_id)
values ('2', 7);
select * from tbl_report order by id;
select * from tbl_post order by id;

select * from tbl_report;
delete from tbl_report;

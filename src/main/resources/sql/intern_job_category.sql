CREATE TABLE tbl_intern_job_category (
    intern_notice_id bigint primary key,
    job_category bigint not null,
    constraint fk_intern_job_category_user foreign key(intern_notice_id)
       references tbl_intern_notice(id),
    constraint fk_user_job_category_job_category foreign key(job_category)
       references tbl_job_category(id)
);

select * from tbl_intern_job_category;

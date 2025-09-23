CREATE TABLE tbl_user_job_category (
    user_id bigint primary key,
    job_category bigint not null,
    constraint fk_user_job_category_user foreign key(user_id)
       references tbl_user(id),
    constraint fk_user_job_category_job_category foreign key(job_category)
       references tbl_job_category(id)
);
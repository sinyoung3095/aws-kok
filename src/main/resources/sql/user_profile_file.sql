CREATE TABLE tbl_user_profile_file (
    file_id bigint primary key,
    user_id bigint not null,
    constraint fk_user_profile_file_file foreign key(file_id)
       references tbl_file(id),
    constraint fk_user_profile_file_user foreign key(user_id)
       references tbl_user(id)
);

select * from tbl_user_profile_file;

insert into tbl_user_profile_file(file_id,user_id)
values (1,1);
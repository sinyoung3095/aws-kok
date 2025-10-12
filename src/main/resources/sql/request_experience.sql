CREATE TABLE tbl_request_experience (
    id bigint generated always as identity primary key,
    request_experience_status request_status  default 'await' not null,
    experience_notice_id bigint not null,
    member_id bigint not null,
    member_alarm_setting_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    request_experience_member_name varchar(255) not null,
    request_experience_member_email varchar(255) not null,
    request_experience_member_phone varchar(255) not null,
    request_experience_member_url varchar(255),
    file_id bigint not null,
    constraint fk_request_experience_experience_notice foreign key(experience_notice_id)
        references tbl_experience_notice(id),
    constraint fk_request_experience_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_request_experience_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id),
    constraint fk_request_experience_file foreign key(file_id)
        references tbl_file(id)
);

select * from tbl_request_experience;
select * from tbl_member;

select * from tbl_request_experience_file;

alter table tbl_request_experience add request_experience_member_name varchar(255) not null;

alter table tbl_request_experience add request_experience_member_email varchar(255) not null;

alter table tbl_request_experience add request_experience_member_phone varchar(255) not null;

alter table tbl_request_experience add request_experience_member_url varchar(255);

alter table tbl_request_experience add file_id bigint not null;

alter table tbl_request_experience add constraint fk_request_experience_file foreign key(file_id) references tbl_file(id);

<<<<<<< HEAD
insert into tbl_request_experience (experience_notice_id, member_id, member_alarm_setting_id)
values (1, 1, 1);

insert into tbl_request_experience (experience_notice_id, member_id, member_alarm_setting_id,request_experience_member_name,request_experience_member_email,request_experience_member_phone,file_id)
values (4, 2, 2,'김신영','ksy3095@naver.com','01051133095',2);
=======
insert into tbl_request_experience (experience_notice_id, member_id, member_alarm_setting_id,request_experience_member_name,request_experience_member_email,request_experience_member_phone,file_id)
values (4, 1, 1,'김신영','ksy3095@naver.com','01012345678',2);
>>>>>>> main-page

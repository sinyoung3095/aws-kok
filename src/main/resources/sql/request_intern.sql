create table tbl_request_intern (
    id bigint generated always as identity primary key,
    request_intern_status request_status not null default 'await',
    member_id bigint not null,
    intern_notice_id bigint not null,
    member_alarm_setting_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_request_intern_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_request_intern_intern_notice foreign key(intern_notice_id)
        references tbl_intern_notice(id),
    constraint fk_request_intern_member_alarm_setting foreign key(member_alarm_setting_id)
        references tbl_member_alarm_setting(id)
);

select * from tbl_request_intern;

select * from tbl_request_intern_file;

alter table tbl_request_intern add request_intern_member_name varchar(255) not null;

alter table tbl_request_intern add request_intern_member_email varchar(255) not null;

alter table tbl_request_intern add request_intern_member_phone varchar(255) not null;

alter table tbl_request_intern add request_intern_member_url varchar(255);

alter table tbl_request_intern add file_id bigint not null;

alter table tbl_request_intern add constraint fk_request_intern_file foreign key(file_id) references tbl_file(id);

insert into tbl_request_intern (intern_notice_id, member_id, member_alarm_setting_id)
values (1, 1, 1);

alter table tbl_request_intern drop evaluation_id;

insert into tbl_request_intern (member_id, intern_notice_id, member_alarm_setting_id)
values (2, 2, 1);

select * from tbl_member;
select * from tbl_user;
select * from tbl_request_intern order by id;
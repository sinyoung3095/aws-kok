CREATE TABLE tbl_post (
    id bigint generated always as identity primary key,
    post_content text not null,
    post_status status default 'active',
    member_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_post_member foreign key(member_id)
        references tbl_member(user_id)
);

select * from tbl_file;

insert into tbl_file (file_origin_name, file_name, file_path, file_size, file_content_type)
values ('test', 'test', '/upload/2025/09/28',
        12345,
        'image/png');

insert into tbl_post_file (file_id, post_id)
values (1, 37);

insert into tbl_post (post_content, member_id)
values ('testing', 1);

insert into tbl_user (user_name, user_phone, user_email, user_password, user_role)
values ('test05', '01012345678', 'test05@gmail.com', '1234', 'member');

insert into tbl_member (user_id)
values (6);

delete from tbl_post_like where post_id = 57;
delete from tbl_post where post_status = 'active';
delete from tbl_post_file where post_id = 54;
delete from tbl_file where file_content_type = 'image/png';

select * from tbl_post;
select * from tbl_post_like;
select * from tbl_member;
select * from tbl_post_file;
select * from tbl_file;

ALTER TABLE tbl_post
    ADD COLUMN likes_count int DEFAULT 0;

insert into tbl_user_job_category (user_id, job_category)
values (6, 15);

insert into tbl_job_category (job_name)
values ('SW 개발'),
       ('데이터/AI'),
       ('기획/전략'),
       ('디자인/UX'),
       ('마케팅/PR'),
       ('경영/운영'),
       ('HR/인사'),
       ('회계/재무'),
       ('법률/법무'),
       ('상담/영업'),
       ('교육/복지'),
       ('HW 개발'),
       ('연구/R&D'),
       ('금융/투자'),
       ('의료/바이오'),
       ('건설/부동산'),
       ('미디어/출판'),
       ('제조/생산'),
       ('운전/물류'),
       ('식품/조리'),
       ('숙박/레저'),
       ('서비스'),
       ('종교인');

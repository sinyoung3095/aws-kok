create type provider as enum('google', 'kakao', 'naver', 'kok');

create table tbl_member (
    user_id bigint primary key,
    member_provider provider default 'kok' not null,
    member_profile_url varchar(255),
    member_info text,
    constraint fk_member_user foreign key(user_id)
        references tbl_user(id)
);

select * from tbl_user;
select * from tbl_report;
select * from tbl_post_like;
delete from tbl_post_like;
select likes_count from tbl_post
where likes_count > 0;
delete from tbl_report;

insert into tbl_member (user_id, member_provider, member_profile_url, member_info)
values
    (11, 'kok', null, '자기소개: 차은우 팬클럽 회장'),
    (12, 'kakao', null, '뮤지컬 좋아함'),
    (13, 'google', null, '여행 덕후'),
    (14, 'naver', null, '사진 취미'),
    (15, 'kok', null, '간단 프로필 없음'),
    (16, 'kok', null, '자기소개: 차은우 팬클럽 회장'),
    (17, 'kakao', null, '뮤지컬 좋아함'),
    (18, 'google', null, '여행 덕후'),
    (19, 'naver', null, '사진 취미'),
    (20, 'kok', null, '간단 프로필 없음'),
    (21, 'kok', null, '자기소개: 차은우 팬클럽 회장'),
    (22, 'kakao', null, '뮤지컬 좋아함'),
    (23, 'google', null, '여행 덕후'),
    (24, 'naver', null, '사진 취미'),
    (25, 'kok', null, '간단 프로필 없음'),
    (26, 'kok', null, '간단 프로필 없음');

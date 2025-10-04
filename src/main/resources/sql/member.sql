create type provider as enum('google', 'kakao', 'naver', 'kok');

create table tbl_member (
    user_id bigint primary key,
    member_provider provider default 'kok' not null,
    member_profile_url varchar(255),
    member_info text,
    constraint fk_member_user foreign key(user_id)
        references tbl_user(id)
);

select * from tbl_member;
select * from tbl_report;
select * from tbl_post_like;
delete from tbl_post_like;
select likes_count from tbl_post
where likes_count > 0;
delete from tbl_report;

insert into tbl_member (user_id, member_provider, member_profile_url, member_info)
values
    (1, 'kok', null, '자기소개: 차은우 팬클럽 회장'),
    (2, 'kakao', null, '뮤지컬 좋아함'),
    (3, 'google', null, '여행 덕후'),
    (4, 'naver', null, '사진 취미'),
    (5, 'kok', null, '간단 프로필 없음');

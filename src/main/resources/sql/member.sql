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

insert into tbl_member (user_id, member_provider, member_profile_url, member_info)
values
    (6, 'kok', null, '자기소개: 차은우 팬클럽 회장'),
    (7, 'kakao', null, '뮤지컬 좋아함'),
    (8, 'google', null, '여행 덕후'),
    (9, 'naver', null, '사진 취미'),
    (10, 'kok', null, '간단 프로필 없음');

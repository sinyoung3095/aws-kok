create type provider as enum('google', 'kakao', 'naver', 'kok');

create table tbl_member (
    user_id bigint primary key,
    member_provider provider default 'kok' not null,
    member_profile_url varchar(255),
    member_info text,
    constraint fk_member_user foreign key(user_id)
        references tbl_user(id)
);
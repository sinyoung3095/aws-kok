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
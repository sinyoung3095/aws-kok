CREATE TABLE tbl_comment (
    id bigint generated always as identity primary key,
    comment_content text not null,
    comment_status status default 'active' not null,
    member_id bigint not null,
    post_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_comment_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_comment_post foreign key(post_id)
        references tbl_post(id)
);

create table  tbl_evaluation (
    id bigint generated always as identity primary key,
    evaluation_content varchar(255) not null,
    evaluation_avg_score double precision not null,
    request_experience_id bigint not null,
    member_id bigint not null,
    company_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint fk_evaluation_request_experience foreign key(request_experience_id)
        references tbl_request_experience(id),
    constraint fk_evaluation_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_evaluation_company foreign key(company_id)
        references tbl_company(user_id)
);

select * from tbl_evaluation order by id;
insert into tbl_evaluation (evaluation_content, evaluation_avg_score, request_experience_id, member_id, company_id)
values ('한 줄 평가5', '4.0', '27', '7', '2');


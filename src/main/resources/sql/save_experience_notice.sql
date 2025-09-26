CREATE TABLE tbl_save_experience_notice (
    member_id bigint primary key,
    experience_notice_id bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    constraint pk_save_experience_notice primary key (member_id, experience_notice_id),
    constraint fk_save_experience_notice_member foreign key(member_id)
        references tbl_member(user_id),
    constraint fk_save_experience_notice_experience_notice foreign key(experience_notice_id)
        references tbl_experience_notice(id)
);

-- ALTER TABLE tbl_save_experience_notice
--     DROP CONSTRAINT tbl_save_experience_notice_pkey;
--
-- ALTER TABLE tbl_save_experience_notice
--     ADD CONSTRAINT pk_save_experience_notice
--         PRIMARY KEY (member_id, experience_notice_id);

select * from tbl_save_experience_notice;

-- member_id 6번 회원이 공고 1, 2 저장
INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (6, 1);

INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (6, 2);

-- member_id 7번 회원이 공고 1, 2 저장
INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (7, 1);

INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (7, 2);

-- member_id 8번 회원이 공고 1, 3 저장
INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (8, 1);

INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (8, 3);

-- member_id 9번 회원이 공고 1, 3 저장
INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (9, 1);

INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (9, 3);

-- member_id 5번 회원이 공고 1, 4 저장
INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (10, 1);

INSERT INTO tbl_save_experience_notice (member_id, experience_notice_id)
VALUES (10, 4);

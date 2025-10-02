CREATE TABLE tbl_payment_user (
                                  id bigint generated always as identity primary key,
                                  payment_id bigint not null,
                                  user_id bigint not null,
                                  request_experience_id bigint,
                                  advertisement_id bigint,
                                  constraint fk_payment_user_payment foreign key(payment_id)
                                      references tbl_payment,
                                  constraint fk_payment_user_user foreign key(user_id)
                                      references tbl_user(id),
                                  constraint fk_payment_user_request_experience foreign key(request_experience_id)
                                      references tbl_request_experience(id),
                                  constraint fk_payment_user_advertisement foreign key(advertisement_id)
                                      references tbl_advertisement(id)
);

select * from tbl_payment_user;

-- 실제 결제된 시간 컬럼 삭제 -> payment로 이동(join해서 사용해야함)
ALTER TABLE tbl_payment_user
    drop COLUMN paid_datetime;
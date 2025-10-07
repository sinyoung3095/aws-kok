CREATE TABLE tbl_advertisement_background_file (
    file_id bigint primary key,
    advertisement_id bigint not null,
    constraint fk_advertisement_background_file foreign key(file_id)
        references tbl_file(id),
    constraint fk_advertisement_background_file_advertisement foreign key(advertisement_id)
        references tbl_advertisement(id)
);

<<<<<<< HEAD
select * from tbl_advertisement_background_file;
=======
select * from tbl_advertisement_background_file;
>>>>>>> console/ad

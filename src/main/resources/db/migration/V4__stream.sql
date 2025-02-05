create table if not exists face_stream_detail
(
    id              bigint primary key auto_increment,
    stream_id       varchar(255) not null,
    time_sheet_type varchar(20)
);


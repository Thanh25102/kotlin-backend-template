create table if not exists face_stream_detail
(
    id              int primary key auto_increment,
    stream_id       int not null,
    time_sheet_type varchar(20),
    branch_id       int not null
);


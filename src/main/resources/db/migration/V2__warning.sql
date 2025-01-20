create table if not exists face_warning_setting
(
    id           serial primary key,
    warning_name varchar(255) not null,
    status       varchar(20)  not null,
    gender       varchar(20)  not null,
    mask         varchar(20)  not null,
    warning_type varchar(20)  not null,
    created_at   timestamp    not null,
    list_id      integer      null,
    range_age    varchar(25)  null
);

create table if not exists face_warning_time
(
    id          serial primary key,
    warning_id  int         not null,
    start_time  time        not null,
    end_time    time        not null,
    day_of_week varchar(50) not null
);

create table if not exists face_warning_camera
(
    id         serial primary key,
    warning_id int not null,
    stream_id  int not null
);

create table if not exists face_warning
(
    id              serial primary key,
    frame_image     varchar(1000) not null,
    warning_name    nvarchar(500) not null,
    warning_time    timestamp     not null,
    warning_cameras nvarchar(255) not null,
    status          varchar(20)   not null
);

create table if not exists face_warning_telegram_session
(
    id        serial primary key,
    chat_id   varchar(255) not null,
    is_active boolean      not null
)
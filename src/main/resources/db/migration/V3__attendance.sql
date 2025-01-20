create table if not exists face_time_sheet
(
    id                  int primary key auto_increment,
    face_item_id        int         not null,
    check_in            timestamp,
    check_out           timestamp,
    check_in_stream_id  int,
    check_out_stream_id int,
    created_at          timestamp            default current_timestamp,
    work_shift_id       int         not null,
    `status`            varchar(20) not null default 'VALID'
);
create table if not exists face_time_sheet_detail
(
    id                int primary key auto_increment,
    time_sheet_id     int           not null,
    title             nvarchar(255) not null,
    stream_id         int           not null,
    camera_type       varchar(50)   not null,
    face_detection_id int           not null,
    created_at        timestamp default current_timestamp
);

create table if not exists face_operational_group
(
    id             int primary key auto_increment,
    title          nvarchar(255)  not null,
    description    nvarchar(1000) not null,
    coefficient    float          not null,
    coefficient_ot float          not null
);

create table if not exists face_operational_group_user
(
    id       int primary key auto_increment,
    group_id int not null,
    user_id  int not null
);

create table if not exists face_operational_hour
(
    id                   int primary key auto_increment,
    title                nvarchar(255) not null,
    start_time           time          not null,
    end_time             time          not null,
    day_of_week          varchar(25)   not null,
    coefficient          float         not null,
    coefficient_ot       float         not null,
    is_overnight         boolean       not null,
    operational_group_id int
);

create table if not exists face_break_time
(
    id         int primary key auto_increment,
    start_time time not null,
    end_time   time not null
);

create table if not exists face_operational_hours_break_time
(
    id                   int primary key auto_increment,
    operational_hours_id int not null,
    break_time_id        int not null
);

create table if not exists face_holiday
(
    id          int primary key auto_increment,
    title       nvarchar(255)  not null,
    description nvarchar(1000) not null,
    start_date  timestamp      not null,
    end_date    timestamp      not null,
    coefficient float          not null
);

create table if not exists face_group_salary
(
    id          int primary key auto_increment,
    title       varchar(255)  not null,
    description varchar(1000) not null,
    salary      double        not null,
    created_at  timestamp default current_timestamp
);

create table if not exists face_user_detail
(
    id                int primary key auto_increment,
    face_list_item_id int    not null,
    hourly_rate       double null,
    gender            int    null,
    group_salary_id   int    null,
    `role` varchar(20) not null default 'USER'
);

create table if not exists face_atd_work_shift
(
    id          int primary key auto_increment,
    title       nvarchar(255)  not null,
    description nvarchar(1000) not null,
    start_time  time           not null,
    end_time    time           not null,
    created_at  timestamp default current_timestamp
);

create table if not exists face_atd_work_shift_user
(
    id           int primary key auto_increment,
    work_shift_id int not null,
    user_id       int not null,
    apply_date    timestamp not null,
    created_at    timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS app_buttons
(
    id               int auto_increment primary key,
    name             nvarchar(255),
    value            int,
    camera_id        nvarchar(255),
    smart_control_id int
);

CREATE TABLE IF NOT EXISTS app_smart_control
(
    id     int auto_increment primary key,
    name   nvarchar(255),
    domain nvarchar(255)
);


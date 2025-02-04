create table if not exists face_branches
(
    id          bigint primary key auto_increment,
    title       varchar(255)  not null,
    description varchar(1000) not null,
    created_at  timestamp default current_timestamp
);
create table if not exists face_branch_group
(
    id        bigint primary key auto_increment,
    branch_id bigint       not null,
    list_id   varchar(255) not null
);
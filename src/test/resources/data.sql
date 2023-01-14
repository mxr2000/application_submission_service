create table t_company
(
    name varchar(50) not null primary key,
    spec text        null
);

create table t_season
(
    id   int auto_increment
        primary key,
    name varchar(20) not null
);

create table t_position
(
    id           int auto_increment
        primary key,
    company_name varchar(50) not null,
    name         varchar(20) not null,
    season_id    int         not null,
    constraint t_position_t_company_id_fk
        foreign key (company_name) references t_company (name),
    constraint t_position_t_season_id_fk
        foreign key (season_id) references t_season (id)
);

create table t_application
(
    id          int auto_increment
        primary key,
    position_id int                                                         not null,
    submit_time date                                                        not null,
    update_time date                                                        not null,
    status      enum ('submitted', 'offer', 'rejected') default 'submitted' not null,
    constraint t_application_t_position_id_fk
        foreign key (position_id) references t_position (id)
);


create table t_update
(
    id             int auto_increment
        primary key,
    application_id int                                                   not null,
    notify_time    date                                                  not null,
    type           enum ('oa', 'bq', 'vr', 'vo1', 'vo2', 'vo3', 'other') not null,
    complete_time  date                                                  null,
    spec           varchar(100)                                          null,
    constraint t_update_t_application_id_fk
        foreign key (application_id) references t_application (id)
);


insert into t_season (name)
values ('2023 Fall Intern');

insert into t_company (name, spec)
values ('TikTok', '');

insert into t_position (company_name, season_id, name)
values ('TikTok', 1, 'SDE');

insert into t_application (position_id, submit_time, update_time)
values (1, '2000-08-05', '2022-08-15');

insert into t_update (application_id, notify_time, type, complete_time, spec)
values (1, '2022-8-15', 'oa', '2022-8-20', 'easy');


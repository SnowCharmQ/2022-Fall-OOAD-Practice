drop table if exists t_user;
create table t_user
(
    user_id  serial primary key,
    username varchar,
    pwd      varchar
);

drop table if exists t_course;
create table t_course
(
    course_id       serial primary key,
    course_name     varchar,
    code            varchar,
    course_language varchar,
    teacher         varchar,
    course_time     timestamp with time zone,
    course_location varchar,
    duration        double precision
);

insert into t_course
values (1, 'OOAD', 'CS309', 'English', 'zyq', current_timestamp, 'Hall', 3.0);

select *
from t_course;
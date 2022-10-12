drop table if exists t_user;
create table t_user
(
    user_id  integer primary key,
    username varchar,
    pwd      varchar
);

drop table if exists t_course;
create table t_course
(
    course_id       integer primary key,
    course_name     varchar,
    code            varchar,
    course_language varchar,
    teacher         varchar,
    course_time     timestamp without time zone,
    duration        double precision
);
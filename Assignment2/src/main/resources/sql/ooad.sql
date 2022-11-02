drop table if exists t_user;
create table t_user
(
    username varchar,
    pwd      varchar
);

drop table if exists t_course;
create table t_course
(
    course_id       varchar primary key,
    course_name     varchar,
    code            varchar,
    course_language varchar,
    teacher         varchar,
    course_time     timestamp with time zone,
    course_location varchar,
    duration        double precision
);

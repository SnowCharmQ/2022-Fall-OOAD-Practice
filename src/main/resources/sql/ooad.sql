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

insert into t_course
values ('1', 'OOAD', 'CS309', 'English', 'zyq', current_timestamp, 'Hall', 3.0);

select *
from t_course;

select *
from t_user;

select *
from t_course a
         join (select to_char(max(course_time), 'yyyy-MM-dd') as time from t_course) b
              on to_char(course_time, 'yyyy-MM-dd') = b.time;

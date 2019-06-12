-- test.sql
drop table dept_copy;

create table dept_copy
as select * from DEPT;

select * from dept_copy;


select *
from scott.emp
start with mgr is null
connect by prior empno=mgr;

drop table emp3;
create table emp3
as select * from emp;


create table names
(
	name varchar2(15) 
)

create table person
(
	no number constraint person_no_pk primary key
	, name varchar2(15) not null
	, age number not null
	, job varchar2(15) not null
)

create sequence person_seq
		start with 1
		increment by 1
		nocycle
		nocache;











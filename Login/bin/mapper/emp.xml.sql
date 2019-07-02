drop table empClone;

create table empClone as select empno, ename, sal, deptno from emp;

drop table deptClone;

create table deptClone as select * from dept;

select * from empClone;
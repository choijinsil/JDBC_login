
drop table membership;
create table membership(
	id varchar2(20) constraint membership_pk primary key,
	pass varchar2(20) not null,
	name varchar2(20) not null,
	ssn1 number(6) not null,
	ssn2 number(7) not null,
	phone varchar2(14),
	addr varchar2(100),
	job varchar2(30)
);

insert into membership values ('gildong','1234','홍길동',900203,1012345,'010-1234-5678','남터','학생');
insert into membership values ('lime','9999','길라임',900203,2012345,'010-2222-5678','고터','학생');
insert into membership values ('juwon','7777','김주원',900801,1012345,'010-3333-5678','상터','학생');
insert into membership values ('gildong2','1235','홍주원',700203,1012345,'010-1234-4321','수원','남원');
select * from membership;

delete from membership where name='카카오';

insert into membership (id,pass,name,ssn1,ssn2) values('admin','manager','관리자',111111,2222222);
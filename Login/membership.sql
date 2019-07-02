


-- membership.sql


create table membership2(
   id      varchar2(20) constraint memberships_pk primary key,
   pass    varchar2(20) not null,
   name    varchar2(20) not null,
   ssn1    number(6)    not null,
   ssn2    number(7)    not null,
   phone   varchar2(14), -- '010-1234-5678'
   addr    varchar2(100),
   job     varchar2(30)
);

-- 컬럼: id,pass,name,ssn1,ssn2,phone,addr,job

insert into membership2 values ('gildong','1234','홍길동',900203,1012345,'010-1234-5678','남터','학생');
insert into membership2 values ('lime','9999','길라임',900203,2012345,'010-2222-5678','고터','학생');
insert into membership2 values ('juwon','7777','김주원',900801,1012345,'010-3333-5678','상터','학생');
insert into membership2(id, pass, name, ssn1, ssn2) values ('admin', 'manager', '관리자', 921126, 2012345);
select * from MEMBERSHIP2;
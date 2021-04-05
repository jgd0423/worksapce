create table member (
    id varchar2(50) not null primary key,
    passwd varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(50),
    regi_date date default sysdate
);

CREATE SEQUENCE seq_exam START WITH 1 INCREMENT BY 1 MINVALUE 1;

create table exam (
    no number not null,
    name varchar2(50) not null,
    socialNumber varchar2(50) not null,
    gender VARCHAR2(1) NOT NULL CHECK (gender IN ('M', 'F')),
    age varchar2(50) not null,
    regi_date date default sysdate
);

select * from exam;
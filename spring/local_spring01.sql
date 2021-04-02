create table member (
    id varchar2(50) not null primary key,
    passwd varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(50),
    regi_date date default sysdate
);
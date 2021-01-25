CREATE TABLE member(
    no NUMBER NOT NULL,
    id VARCHAR2(50) NOT NULL,
    passwd VARCHAR2(250) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    gender VARCHAR2(1) NOT NULL CHECK(gender IN ('M', 'F')),
    bornYear NUMBER NOT NULL,
    postcode VARCHAR2(100) NOT NULL,
    address VARCHAR2(100) NOT NULL,
    detailAddress VARCHAR2(100) NOT NULL,
    extraAddress VARCHAR2(100) NULL,
    regiDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    unique(no)
);

CREATE SEQUENCE seq_member START WITH 1 INCREMENT BY 1 MINVALUE 1;

SELECT * FROM member ORDER BY no desc;

select * from member where id = 'hong' and passwd = '1';

UPDATE member SET bornYear = 1000 WHERE no = '1';

commit;

CREATE TABLE memo(
    no NUMBER NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    content VARCHAR2(250) NOT NULL,
    regiDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(no)
);

CREATE SEQUENCE seq_memo START WITH 1 INCREMENT BY 1 MINVALUE 1;

SELECT * FROM memo ORDER BY no;

SELECT COUNT(*) FROM member WHERE id = 'hong';

SELECT * FROM 
    (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM member WHERE no > 0 ORDER BY no DESC) a) 
WHERE Rnum >= 10 AND Rnum <= 20;





begin
for i in 1 .. 545 loop
insert into member values
(seq_member.nextval, i, i, i, 'M', 1999, 20202, 'a', 'b', 'c', current_Timestamp);
end loop;
commit;
end;
/



CREATE TABLE guestbook(
    no NUMBER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL,
    passwd VARCHAR2(50) NOT NULL,
    content CLOB NOT NULL,
    regiDate DATE DEFAULT SYSDATE,
    PRIMARY KEY(no)
);

CREATE SEQUENCE seq_guestbook START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;

SELECT * FROM guestbook;



CREATE TABLE survey(
    no NUMBER NOT NULL,
    question VARCHAR2(4000) NOT NULL,
    ans1 VARCHAR2(500) NOT NULL,
    ans2 VARCHAR2(500) NOT NULL,
    ans3 VARCHAR2(500) NOT NULL,
    ans4 VARCHAR2(500) NOT NULL,
    status CHAR(1) DEFAULT '1',
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    regi_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(no)
);

CREATE SEQUENCE seq_survey START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;


CREATE TABLE survey_answer (
    answer_no NUMBER NOT NULL PRIMARY KEY,
    no NUMBER NOT NULL REFERENCES survey(no),
    answer NUMBER NOT NULL,
    regi_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE seq_survey_answer START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;

SELECT * FROM survey ORDER BY no;
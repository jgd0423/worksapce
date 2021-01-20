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
    extraAddress VARCHAR2(100) NOT NULL,
    regiDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    unique(no)
);

CREATE SEQUENCE seq_member START WITH 1 INCREMENT BY 1 MINVALUE 1;

SELECT * FROM member ORDER BY no;

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
CREATE TABLE board (
    no NUMBER NOT NULL, -- 일련번호
    num NUMBER NOT NULL, -- 순번 = ref
    writer VARCHAR2(50) NOT NULL,
    subject VARCHAR2(50) NOT NULL,
    content CLOB NOT NULL,
    email VARCHAR2(50) NOT NULL,
    passwd VARCHAR2(50) NOT NULL,
    ref NUMBER NOT NULL, -- 가족의 그룹화
    re_step NUMBER NOT NULL, -- 가족의 계층
    re_level NUMBER NOT NULL, -- 전체글의 순서
    re_parent NUMBER NOT NULL,
    hit NUMBER NOT NULL, -- 조회수
    regi_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(no)
);




begin
for i in 1 .. 1000 loop
insert into board values
(seq_board.nextval, i, 'w' || i, 's' || i, 'c' || i, 'e' || i, 'p' || i, i, 1, 1, 0, 0, current_Timestamp);
end loop;
commit;
end;
/

SELECT * FROM board order by no desc;

SELECT COUNT(*) FROM board WHERE re_parent = 3024;

select * from board where ref = 1001 ORDER BY ref DESC, re_level ASC;

select * from board where re_parent = 3025;

select b.*, (select count(*) from board where re_parent = b.no) pcounter from board b;

select b.*, (select count(*) from board where re_parent = b.no) pcounter from board b where no = 3024;

select b.*, (select count(*) from board where re_parent = b.no) pcounter from board b ORDER BY ref DESC, re_level ASC;

select count(*) from board where re_parent = no;

select (
select count(*) from board where re_parent = b.no) from board b ;




SELECT * FROM board ORDER BY ref DESC, re_level ASC;

SELECT * FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3027;

SELECT rnum, no FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE rnum BETWEEN ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3025) - 1) AND ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3025) + 1);


SELECT MAX(rnum) FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a);

(SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3025);


SELECT rnum, no FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 2024) - 1) OR rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 2024) + 1);



SELECT rnum, no FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 2024));


SELECT rnum, no FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 2024));


SELECT rnum, no, (SELECT MAX(ROWNUM) FROM board) MAXRnum
FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) 
WHERE rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3025) - 1) 
OR rnum = ((SELECT rnum FROM (SELECT ROWNUM Rnum, a.* FROM (SELECT * FROM board ORDER BY ref DESC, re_level ASC) a) WHERE no = 3025) + 1);

SELECT MAX(ROWNUM) MAXRnum FROM board;

SELECT * FROM board ORDER BY ref DESC, re_level ASC;


select * 
from
    (select 
        b.*, 
        lag(no) over(order by ref desc, re_level asc) preNo, 
        lag(subject) over (order by ref desc, re_level asc) preSubject, 
        lead(no) over (order by ref desc, re_level asc) nxtNo, 
        lead(subject) over (order by ref desc, re_level asc) nxtSubject 
    from board b order by ref desc, re_level asc) 
where no = 3024;
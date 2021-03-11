CREATE TABLE student (
    no NUMBER NOT NULL,
    id VARCHAR2(50) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    phone VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL,
    address VARCHAR2(500) NOT NULL,
    grade VARCHAR2(1) NOT NULL CHECK (grade IN ('1', '2', '3')),
    classes VARCHAR2(50) NOT NULL,
    regiDate DATE DEFAULT SYSDATE,
    PRIMARY KEY(id),
    UNIQUE(no)
);

CREATE SEQUENCE seq_student START WITH 1 INCREMENT BY 1 MINVALUE 1;

drop table student;

SELECT * FROM student;

CREATE TABLE exam (
    no NUMBER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    regiDate DATE DEFAULT SYSDATE,
    PRIMARY KEY(no)
);

CREATE SEQUENCE seq_exam START WITH 1 INCREMENT BY 1 MINVALUE 1;

SELECT * FROM exam;

CREATE TABLE score (
    no NUMBER NOT NULL,
    studentId VARCHAR2(50) NOT NULL,
    examId NUMBER NOT NULL,
    korean NUMBER NOT NULL,
    english NUMBER NOT NULL,
    math NUMBER NOT NULL,
    science NUMBER NOT NULL,
    history NUMBER NOT NULL,
    regiDate DATE DEFAULT SYSDATE,
    PRIMARY KEY(no)
);

select * from score;

ALTER TABLE score ADD CONSTRAINT fk_score_studentId FOREIGN KEY(studentId) REFERENCES student(id);
ALTER TABLE score ADD CONSTRAINT fk_score_examId FOREIGN KEY(examId) REFERENCES exam(no);

ALTER TABLE score drop CONSTRAINT fk_score_studentId;
ALTER TABLE score drop CONSTRAINT fk_score_examId;

CREATE SEQUENCE seq_score START WITH 1 INCREMENT BY 1 MINVALUE 1;

select 
    score.no no, 
    student.name studentName,
    score.studentid studentId,
    exam.name examName,
    exam.no examId,
    score.korean korean,
    score.english english,
    score.math math,
    score.science science,
    score.history history,
    (score.korean + score.english + score.math + score.science + score.history) totalScore,
    ((score.korean + score.english + score.math + score.science + score.history) / 5) averageScore,
    score.regiDate regiDate
from score left outer join student on score.studentId = student.id left outer join exam on score.examId = exam.no;

create or replace view scoreView
as
select 
    score.no no, 
    student.name studentName,
    score.studentid studentId,
    exam.name examName,
    exam.no examId,
    score.korean korean,
    score.english english,
    score.math math,
    score.science science,
    score.history history,
    (score.korean + score.english + score.math + score.science + score.history) totalScore,
    ((score.korean + score.english + score.math + score.science + score.history) / 5) averageScore,
    score.regiDate regiDate
from score left outer join student on score.studentId = student.id left outer join exam on score.examId = exam.no;

select * from scoreView;


select score.no no, student.name studentName, exam.name examName, score.korean korean, score.english english, score.math math, score.science science, score.history history, score.regiDate regiDate from score inner join student on score.studentId = student.id inner join exam on score.examId = exam.no;
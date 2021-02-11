CREATE TABLE DEPARTMENT( 
DNO INT PRIMARY KEY,
DNAME VARCHAR2(20), LOC VARCHAR2(20)
)
CREATE TABLE EMPLOYEE(  
ENO INT PRIMARY KEY,
ENAME VARCHAR2(20), JOB VARCHAR2(20),
MANAGER INT, HIREDATE date,
SALARY INT, COMMISSION INT,
DNO INT REFERENCES DEPARTMENT(DNO)
)
CREATE TABLE SALGRADE(   
GRADE INT PRIMARY KEY,
LOWSAL INT, HIGHSAL INT
)

CREATE SEQUENCE seq_salgrade START WITH 1 INCREMENT BY 1 MINVALUE 1;

INSERT INTO DEPARTMENT VALUES(10, 'Accounting', 'Seoul'); INSERT INTO DEPARTMENT VALUES(20, 'Human', 'Incheon');
INSERT INTO DEPARTMENT VALUES(30, 'Sales', 'Yungin'); INSERT INTO DEPARTMENT VALUES(40, 'Computing', 'Suwon');

INSERT INTO SALGRADE VALUES(seq_salgrade.nextval, 901, 1000); INSERT INTO SALGRADE VALUES(seq_salgrade.nextval, 501, 900);
INSERT INTO SALGRADE VALUES(seq_salgrade.nextval, 401, 500);  INSERT INTO SALGRADE VALUES(seq_salgrade.nextval, 301, 400);
INSERT INTO SALGRADE VALUES(seq_salgrade.nextval, 201, 300);

INSERT INTO EMPLOYEE VALUES(101, 'e1', 'staff', 113, '2007-03-01', 300, NULL, 20);
INSERT INTO EMPLOYEE VALUES(102, 'e2', 'deputy', 105, '2007-04-02', 250,   80, 30);
INSERT INTO EMPLOYEE VALUES(103, 'e3', 'section', 105, '2005-02-10', 500,  100, 30);
INSERT INTO EMPLOYEE VALUES(104, 'e4', 'chief', 108, '2003-09-02', 600, NULL, 20);
INSERT INTO EMPLOYEE VALUES(105, 'e5', 'section', 105, '2005-04-07', 450,  200, 30);
INSERT INTO EMPLOYEE VALUES(106, 'e6', 'chief', 108, '2003-10-09', 480, NULL, 30);
INSERT INTO EMPLOYEE VALUES(107, 'e7', 'chief', 108, '2004-01-08', 520, NULL, 10);
INSERT INTO EMPLOYEE VALUES(108, 'e8', 'senior', 103, '2004-03-08', 500,    0, 30);
INSERT INTO EMPLOYEE VALUES(109, 'e9', 'ceo', NULL, '1996-10-04',1000, NULL, 20);
INSERT INTO EMPLOYEE VALUES(110, 'e10', 'section', 103, '2005-04-07', 500, NULL, 10);
INSERT INTO EMPLOYEE VALUES(111, 'e11', 'staff', 107, '2007-03-01', 280, NULL, 30);
INSERT INTO EMPLOYEE VALUES(112, 'e12', 'staff', 106, '2007-08-09', 300, NULL, 20);
INSERT INTO EMPLOYEE VALUES(113, 'e13', 'chief', 103, '2002-10-09', 560, NULL, 20);
INSERT INTO EMPLOYEE VALUES(114, 'e14', 'staff', 106, '2007-11-09', 250, NULL, 10);

commit;

SELECT * FROM department;

SELECT * FROM employee;

SELECT * FROM salgrade;

SELECT * FROM employee, department;

SELECT e1.ename
FROM employee e1, employee e2
WHERE e1.salary = e2.salary;

SELECT employee.ename, salgrade.grade
FROM employee, salgrade
WHERE salgrade.lowsal <= employee.salary AND employee.salary <= salgrade.highsal
ORDER BY grade;

SELECT employee.ename, salgrade.grade
FROM employee, salgrade
WHERE employee.salary BETWEEN salgrade.lowsal AND salgrade.highsal
ORDER BY grade;

SELECT e.ename AS employeeName, m.ename AS managerName
FROM employee e, employee m
WHERE e.manager = m.eno;

SELECT ename, grade, dname
FROM employee, salgrade, department
WHERE salary BETWEEN lowsal AND highsal
AND employee.dno = department.dno;

SELECT employee.ename, department.dname
FROM employee CROSS JOIN department;

SELECT ename, dname
FROM employee INNER JOIN department ON (employee.dno = department.dno);

SELECT member.ename AS employeeName, manager.ename AS managerName
FROM employee member LEFT OUTER JOIN employee manager
ON member.manager = manager.eno;

SELECT dname
FROM department
WHERE 
    department.dno = (
    SELECT dno 
    FROM employee 
    WHERE employee.eno = 103
    );
    
SELECT eno, dno
FROM employee
WHERE 
    employee.dno = (
    SELECT dno 
    FROM employee 
    WHERE employee.eno = 110
    );

SELECT ename, salary, dno
FROM employee
WHERE 
    dno IN (
    SELECT dno 
    FROM employee
    WHERE salary >= 500
    );
    
SELECT eno, dno, salary
FROM employee
WHERE
    employee.salary > ANY (
    SELECT salary FROM employee WHERE dno = 20
    );
    
SELECT eno, dno, salary
FROM employee
WHERE
    employee.salary > ALL (
    SELECT salary FROM employee WHERE dno = 10
    );

SELECT ename
FROM employee
WHERE
    EXISTS (
    SELECT * FROM employee WHERE salary + commission > 500
    );

SELECT eno, ename, salary, dno
FROM employee
WHERE dno IN (SELECT dno FROM employee WHERE eno = 101)
AND salary IN (SELECT salary FROM employee WHERE eno = 101);

SELECT eno, ename, salary, dno
FROM employee e
WHERE dno IN (SELECT dno FROM employee m WHERE e.manager = m.eno);

(SELECT * FROM employee WHERE dno = 10)
UNION
(SELECT * FROM employee WHERE job = 'staff');

(SELECT * FROM employee WHERE dno = 10)
INTERSECT
(SELECT * FROM employee WHERE job = 'staff');

(SELECT * FROM employee WHERE dno = 10)
MINUS
(SELECT * FROM employee WHERE job = 'staff');

(SELECT * FROM employee WHERE dno = 10)
UNION ALL
(SELECT * FROM employee WHERE job = 'staff');

(SELECT eno, ename, dno, NULL FROM employee)
UNION
(SELECT NULL, NULL, dno, dname FROM department);

SELECT AVG(salary) FROM employee;

SELECT MAX(salary), MIN(salary) FROM employee;

SELECT AVG(salary), VARIANCE(salary), STDDEV(salary) FROM employee;

SELECT COUNT(*), COUNT(COMMISSION) FROM employee;

--SELECT COUNT(job) COUNT(DISTINCT job) FROM employee; 오라클에서 어케함?

SELECT dno, AVG(salary) FROM employee GROUP BY dno;

SELECT dno, AVG(salary) FROM employee GROUP BY dno HAVING MAX(salary) > 500;

SELECT dno, job, SUM(salary) FROM employee GROUP BY dno, job;

SELECT dno, job, SUM(salary) FROM employee GROUP BY ROLLUP(dno, job);

SELECT dno, job, SUM(salary) FROM employee GROUP BY CUBE(dno, job);

SELECT dno, job, SUM(salary) FROM employee GROUP BY GROUPING SETS(dno, job);

SELECT dno, AVG(salary) FROM employee GROUP BY dno HAVING COUNT(*) > 3;

SELECT COUNT(*) FROM EMPLOYEE GROUP BY dno;

SELECT ename, salary, RANK() OVER(ORDER BY salary DESC) AS rank FROM employee;

SELECT ename, salary, DENSE_RANK() OVER(ORDER BY salary DESC) AS rank FROM employee;

SELECT ename, salary, ROW_NUMBER() OVER(ORDER BY salary DESC) AS rank FROM employee;

SELECT ename, salary, NTILE(5) OVER(ORDER BY salary DESC) AS rank FROM employee;

SELECT ename, salary, dno, RANK() OVER(PARTITION BY dno ORDER BY salary DESC) AS rank_dept FROM employee;

SELECT *
FROM
    (SELECT ename, salary, dno, RANK() OVER(PARTITION BY dno ORDER BY salary DESC) AS rank_val FROM employee)
WHERE rank_val = 2;

--SELECT ename, salary, dno, RANK() OVER(PARTITION BY dno ORDER BY salary DESC) AS rank_val FROM employee WHERE rank_val = 2; -- not working

SELECT dno, AVG(salary) OVER(PARTITION BY dno) AS avg_sal_dept FROM employee;

SELECT DISTINCT dno, FIRST_VALUE(salary) OVER(PARTITION BY dno ORDER BY salary  DESC) AS highest_sal FROM employee;

SELECT 
    DISTINCT ename, 
    salary, 
    LAG(salary, 1) OVER(ORDER BY salary DESC) AS LAG_VAL,
    LEAD(salary, 1) OVER(ORDER BY salary DESC) AS LEAD_VAL
FROM employee;

CREATE UNIQUE INDEX idx_dname_unique ON department(dname);

CREATE INDEX idx_loc_unique ON department(loc);

CREATE INDEX idx_dnosalary ON employee(dno, salary);

CREATE INDEX idx_dnosalary_desc ON employee(dno ASC, salary DESC);

SELECT * FROM employee WHERE ename = 'e1';

CREATE VIEW EMP30
AS 
SELECT *
FROM employee
WHERE dno = 30;

SELECT * FROM emp30;

INSERT INTO emp30 VALUES (32423, 'e22', 'dfsdf', '105', sysdate, '500', '40', '20'); -- 이렇게 하면 employee 테이블에 값 들어가게 됨

SELECT ename FROM EMP30 WHERE salary >= 500;

CREATE VIEW EMPAVGSAL
AS
SELECT AVG(salary) AS SALAVG
FROM employee;

SELECT * FROM empavgsal;

UPDATE EMPAVGSAL SET SALAVG = SALAVG + 10;

SELECT dno, AVG(salary) AS AVG_SAL
FROM employee
GROUP BY dno;

SELECT dname, AVG(salary)
FROM department d, employee e
WHERE d.dno = e.dno
GROUP BY dname;

SELECT dname, AVG_SAL
FROM (SELECT dno, AVG(salary) AS AVG_SAL FROM employee GROUP BY dno) s, department d
WHERE s.dno = d.dno;

WITH S(dno, AVG_SAL)
AS(SELECT dno, AVG(salary)
    FROM employee
    GROUP BY dno)
SELECT dname, AVG_SAL
FROM S, department
WHERE department.dno = S.dno;


CREATE OR REPLACE PROCEDURE emp_pro
AS
    v_ename employee.ename%type;
    v_job employee.job%type;
    v_salary employee.job%type;
BEGIN
    SELECT ename, job, salary INTO v_ename, v_job, v_salary
    FROM employee
    WHERE eno = 109;
    DBMS_OUTPUT.PUT_LINE(v_ename || ' / ' ||  v_job || ' / ' || v_salary);
END;

SET SERVEROUTPUT ON;
EXEC emp_pro;


CREATE OR REPLACE PROCEDURE emp_pro_para
(
    enumber NUMBER
)
AS
    v_eno employee.eno%type;
    v_ename employee.ename%type;
    v_job employee.job%type;
    v_salary employee.job%type;
BEGIN
    SELECT eno, ename, job, salary INTO v_eno, v_ename, v_job, v_salary
    FROM employee
    WHERE eno = enumber;
    DBMS_OUTPUT.PUT_LINE(v_eno || ' / ' || v_ename || ' / ' || v_job || ' / ' || v_salary);
END;

SET SERVEROUTPUT ON;
EXEC emp_pro_para(102);


CREATE OR REPLACE PROCEDURE emp_out_put_para
(
    did IN NUMBER,
    avg_sal OUT NUMBER
)
AS
BEGIN
    SELECT AVG(salary) INTO avg_sal FROM employee WHERE employee.dno = did;
END;

SET SERVEROUTPUT ON;
VARIABLE avg_sal NUMBER; -- OUT값을 받을 변수 선언
EXECUTE emp_out_put_para(30, :avg_sal);
PRINT avg_sal;

SELECT MAX(salary) FROM employee WHERE dno = 30;


CREATE OR REPLACE FUNCTION max_sal
(
    param IN NUMBER
)
RETURN NUMBER
IS max_val NUMBER;
BEGIN
    SELECT MAX(salary) INTO max_val FROM employee WHERE dno = param;
    RETURN max_val;
END;

SELECT * FROM employee WHERE salary = max_sal(30);






SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM salgrade;
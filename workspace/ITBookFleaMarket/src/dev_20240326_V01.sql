

/* Drop Tables */

DROP TABLE t_account CASCADE CONSTRAINTS;
DROP TABLE t_tradePost CASCADE CONSTRAINTS;
DROP TABLE t_tradeLog CASCADE CONSTRAINTS;
DROP TABLE t_book CASCADE CONSTRAINTS;
DROP TABLE t_qna CASCADE CONSTRAINTS;
DROP TABLE t_quiz_attend CASCADE CONSTRAINTS;
DROP TABLE t_rtAttend CASCADE CONSTRAINTS;
DROP TABLE t_member CASCADE CONSTRAINTS;
DROP TABLE t_quiz CASCADE CONSTRAINTS;
DROP TABLE t_roulette CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_transactionNo;
DROP SEQUENCE SEQ_bookNo;
DROP SEQUENCE SEQ_qno;
DROP SEQUENCE SEQ_qzAttendNo;
DROP SEQUENCE SEQ_qzNo;
DROP SEQUENCE SEQ_rtAttendNo;
DROP SEQUENCE SEQ_trLogNo;
DROP SEQUENCE SEQ_postNo;

ALTER TABLE t_account MODIFY transactiontype VARCHAR(10);


/* Create Sequences */

CREATE SEQUENCE SEQ_transactionNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_bookNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_qno INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_qzAttendNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_qzNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_rtAttendNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_trLogNo INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_postNo INCREMENT BY 1 START WITH 1 NOCACHE;


/* Create Tables */

CREATE TABLE t_account
(
	transactionNo number NOT NULL,
	transactionType varchar2(2) NOT NULL,
	accountHolder varchar2(20) NOT NULL,
	transactionId varchar2(20) NOT NULL UNIQUE,
	amount number,
	transactionDate date,
	PRIMARY KEY (transactionNo)
);


CREATE TABLE t_book
(
	bookNo number NOT NULL,
	bookName varchar2(10) NOT NULL,
	bookGroup varchar2(10) NOT NULL,
	bookPrice number NOT NULL,
	bookState varchar2(2) NOT NULL,
	author varchar2(10) NOT NULL,
	publisher varchar2(10) NOT NULL,
	isbn number NOT NULL,
	seller varchar2(20) NOT NULL UNIQUE,
	PRIMARY KEY (bookNo)
);


CREATE TABLE t_member
(
	id varchar2(20) PRIMARY KEY,
	pw varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	email varchar2(50) NOT NULL,
	accountNo number NOT NULL,
	reportNo number,
	isInactivated char(1),
	inactiveDate date,
	isAdmin char(1),
	joinDate date,
	mileage number,
	purchaseCnt number,
	saleCnt number
);


CREATE TABLE t_qna
(
	qno number NOT NULL,
	qId varchar2(20) NOT NULL UNIQUE,
	questionTitle varchar2(10) NOT NULL,
	questionContent varchar2(100) NOT NULL,
	questioRregdate date DEFAULT SYSDATE NOT NULL,
	answerState varchar2(5),
	answerContent varchar2(100),
	answerDate date,
	PRIMARY KEY (qno)
);


CREATE TABLE t_quiz
(
	qzNo number NOT NULL,
	qzTitle varchar2(100),
	qzCorrectNum number,
	qzReward number,
	qzStartDate date,
	endDate date,
	qzRegDate date,
	qzModDate date,
	PRIMARY KEY (qzNo)
);


CREATE TABLE t_quiz_attend
(
	qzAttendNo number NOT NULL,
	qzNo number NOT NULL,
	id varchar2(20) NOT NULL UNIQUE,
	qzAnswer number,
	qzAttendDate date,
	PRIMARY KEY (qzAttendNo)
);


CREATE TABLE t_roulette
(
	prizeNo number NOT NULL,
	prizeName varchar2(20) NOT NULL,
	winningPct number NOT NULL,
	stoke number,
	PRIMARY KEY (prizeNo)
);


CREATE TABLE t_rtAttend
(
	rtAttendNo number NOT NULL,
	rtWonPrizeNo number NOT NULL,
	rtAttendId varchar2(20) NOT NULL UNIQUE,
	rtAttendDate date,
	PRIMARY KEY (rtAttendNo)
);


CREATE TABLE t_tradeLog
(
	trLogNo number PRIMARY KEY,
	bookNo number NOT NULL,
	tradePrice number NOT NULL,
	tradeDate date NOT NULL,
	tradeState varchar2(5) NOT NULL,
	invoiceNo number,
	buyer varchar2(20) NOT NULL UNIQUE,
	usedMileage number
);


CREATE TABLE t_tradePost
(
	postNo number NOT NULL,
	bookNo number NOT NULL,
	trLogNo number NOT NULL UNIQUE,
	memo varchar2(1000),
	postRegdate date,
	postHitNo number,
	PRIMARY KEY (postNo)
);



/* Create Foreign Keys */

ALTER TABLE t_tradeLog
	ADD FOREIGN KEY (bookNo)
	REFERENCES t_book (bookNo)
;


ALTER TABLE t_tradePost
	ADD FOREIGN KEY (bookNo)
	REFERENCES t_book (bookNo)
;


ALTER TABLE t_account
	ADD FOREIGN KEY (transactionId)
	REFERENCES t_member (id)
;


ALTER TABLE t_book
	ADD FOREIGN KEY (seller)
	REFERENCES t_member (id)
;


ALTER TABLE t_qna
	ADD FOREIGN KEY (qId)
	REFERENCES t_member (id)
;


ALTER TABLE t_quiz_attend
	ADD FOREIGN KEY (id)
	REFERENCES t_member (id)
;


ALTER TABLE t_rtAttend
	ADD FOREIGN KEY (rtAttendId)
	REFERENCES t_member (id)
;


ALTER TABLE t_tradeLog
	ADD FOREIGN KEY (buyer)
	REFERENCES t_member (id)
;


ALTER TABLE t_quiz_attend
	ADD FOREIGN KEY (qzNo)
	REFERENCES t_quiz (qzNo)
;


ALTER TABLE t_rtAttend
	ADD FOREIGN KEY (rtWonPrizeNo)
	REFERENCES t_roulette (prizeNo)
;


ALTER TABLE t_tradePost
	ADD FOREIGN KEY (trLogNo)
	REFERENCES t_tradeLog (trLogNo)
;

INSERT INTO t_emp(empno, ename, hiredate)
       VALUES(5555, 'Ohh', TO_DATE('28-02-2024', 'DD/MM/YYYY'));

SELECT TO_CHAR(SYSDATE, 'YYYY. MM. DD PM HH:MM:SS') FROM dual;


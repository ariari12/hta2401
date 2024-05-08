
/* Drop Triggers */

DROP TRIGGER TRI_t_qna_qno;
DROP TRIGGER TRI_t_quiz_attend_aNo;
DROP TRIGGER TRI_t_quiz_attend_attendNo;
DROP TRIGGER TRI_t_quiz_quizNo;
DROP TRIGGER TRI_t_tradePost_postNo;
DROP TRIGGER TRI_t_trade_tradeNo;



/* Drop Tables */

DROP TABLE t_qna CASCADE CONSTRAINTS;
DROP TABLE t_quiz_attend CASCADE CONSTRAINTS;
DROP TABLE t_tradePost CASCADE CONSTRAINTS;
DROP TABLE t_member CASCADE CONSTRAINTS;
DROP TABLE t_quiz CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_t_qna_qno;
DROP SEQUENCE SEQ_t_quiz_attend_aNo;
DROP SEQUENCE SEQ_t_quiz_attend_attendNo;
DROP SEQUENCE SEQ_t_quiz_quizNo;
DROP SEQUENCE SEQ_t_tradePost_postNo;
DROP SEQUENCE SEQ_t_trade_tradeNo;




/* Create Sequences */

CREATE SEQUENCE SEQ_t_qna_qno INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_t_quiz_attend_aNo INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_t_quiz_attend_attendNo INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_t_quiz_quizNo INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_t_tradePost_postNo INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_t_trade_tradeNo INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE t_member
(
	id varchar2(20) NOT NULL UNIQUE,
	pw varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	email varchar2(50) NOT NULL,
	account number NOT NULL,
	report number,
	isInactivated char(1),
	InactiveDate date,
	isAdmin char(1),
	joinDate date,
	mileage number,
	purchaseCnt number,
	SaleCnt number,
	PRIMARY KEY (id)
);


CREATE TABLE t_qna
(
	qno number NOT NULL,
	id varchar2(20) NOT NULL UNIQUE,
	regdate date DEFAULT SYSDATE,
	PRIMARY KEY (qno)
);


CREATE TABLE t_quiz
(
	quizNo number NOT NULL,
	quizTitle varchar2(100),
	correctNum number,
	reward number,
	startDate date,
	endDate date,
	regDate date,
	modDate date,
	PRIMARY KEY (quizNo)
);


CREATE TABLE t_quiz_attend
(
	attendNo number NOT NULL,
	quizNo number NOT NULL,
	id varchar2(20) NOT NULL UNIQUE,
	answer number,
	attendDate date,
	PRIMARY KEY (attendNo)
);


CREATE TABLE t_tradePost
(
	postNo number NOT NULL,
	seller varchar2(20) NOT NULL UNIQUE,
	bookName_FKChange varchar2(100) NOT NULL,
	bookPrice number NOT NULL,
	bookState varchar2(2) NOT NULL,
	memo varchar2(1000),
	postRegdate date,
	tradeState varchar2(10),
	buyer varchar2(20) NOT NULL UNIQUE,
	postHitNo number,
	PRIMARY KEY (postNo)
);



/* Create Foreign Keys */

ALTER TABLE t_qna
	ADD FOREIGN KEY (id)
	REFERENCES t_member (id)
;


ALTER TABLE t_quiz_attend
	ADD FOREIGN KEY (id)
	REFERENCES t_member (id)
;


ALTER TABLE t_tradePost
	ADD FOREIGN KEY (buyer)
	REFERENCES t_member (id)
;


ALTER TABLE t_tradePost
	ADD FOREIGN KEY (seller)
	REFERENCES t_member (id)
;


ALTER TABLE t_quiz_attend
	ADD FOREIGN KEY (quizNo)
	REFERENCES t_quiz (quizNo)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_t_qna_qno BEFORE INSERT ON t_qna
FOR EACH ROW
BEGIN
	SELECT SEQ_t_qna_qno.nextval
	INTO :new.qno
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_t_quiz_attend_aNo BEFORE INSERT ON t_quiz_attend
FOR EACH ROW
BEGIN
	SELECT SEQ_t_quiz_attend_aNo.nextval
	INTO :new.aNo
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_t_quiz_attend_attendNo BEFORE INSERT ON t_quiz_attend
FOR EACH ROW
BEGIN
	SELECT SEQ_t_quiz_attend_attendNo.nextval
	INTO :new.attendNo
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_t_quiz_quizNo BEFORE INSERT ON t_quiz
FOR EACH ROW
BEGIN
	SELECT SEQ_t_quiz_quizNo.nextval
	INTO :new.quizNo
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_t_tradePost_postNo BEFORE INSERT ON t_tradePost
FOR EACH ROW
BEGIN
	SELECT SEQ_t_tradePost_postNo.nextval
	INTO :new.postNo
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_t_trade_tradeNo BEFORE INSERT ON t_trade
FOR EACH ROW
BEGIN
	SELECT SEQ_t_trade_tradeNo.nextval
	INTO :new.tradeNo
	FROM dual;
END;

/





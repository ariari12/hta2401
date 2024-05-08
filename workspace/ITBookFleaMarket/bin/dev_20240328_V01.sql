ALTER TABLE t_qna MODIFY questionTitle VARCHAR2(100);
ALTER TABLE t_qna MODIFY questionContent VARCHAR2(300);

ALTER TABLE t_qna MODIFY answerState VARCHAR2(20);

ALTER TABLE t_qna RENAME COLUMN QUESTIORREGDATE TO questionDate;

DROP TABLE t_review CASCADE CONSTRAINTS;

CREATE TABLE t_review
(
	reviewNo number NOT NULL,
	trLogNo number NOT NULL UNIQUE,
	rate number NOT NULL,
	reviewContent varchar2(1000),
	reviewDate date,
	PRIMARY KEY (reviewNo)
);

ALTER TABLE t_review
	ADD FOREIGN KEY (trLogNo)
	REFERENCES t_tradeLog (trLogNo)
;

CREATE SEQUENCE SEQ_miLogNo INCREMENT BY 1 START WITH 1 NOCACHE;

INSERT INTO t_qna(qNo, qId, questionTitle, questionContent, questionDate, answerState) 
VALUES (SEQ_QNO.NEXTVAL, ?, ?, ?, SYSDATE, '접수대기');

SELECT * FROM t_qna WHERE answerState != '답변완료';

DROP TABLE t_account CASCADE CONSTRAINTS;

CREATE TABLE t_account
(
	transactionNo number NOT NULL,
	transactionType varchar2(2) NOT NULL,
	accountHolder varchar2(20) NOT NULL,
	amount number,
	transactionDate date,
	trLogNo number NOT NULL UNIQUE,
	PRIMARY KEY (transactionNo)
);

ALTER TABLE t_account
	ADD FOREIGN KEY (trLogNo)
	REFERENCES t_tradeLog (trLogNo);
    
    

SELECT trlogno, l.bookno, tradeprice, tradedate, tradestate, invoiceno, buyer, mllogno
FROM t_tradeLog l, t_book b 
WHERE  l.bookno = b.bookno AND (seller = 'aaa' OR buyer = 'aaa');
    
    
SELECT a.*
FROM t_account a, t_tradeLog l, t_book b
WHERE a.trLogNo = l.trLogNo AND l.bookNo = b.bookNo
AND ( (seller = 'aaa' AND (transactionType = '입금' OR (tradeState = '구매취소' AND transactionType = '출금')))
OR (buyer = 'aaa' AND tradeState = '구매취소' AND transactionType = '출금') )
ORDER BY transactionNo;


ALTER TABLE t_account ADD transactionId VARCHAR(20) REFERENCES t_member(id);

UPDATE t_qna SET answerContent = NULL WHERE qNo = ?;

DROP TABLE t_quiz CASCADE CONSTRAINTS;

CREATE TABLE t_quiz
(
   qzNo number NOT NULL,
   qzTitle varchar2(1000),
   qzNum1 varchar2(100) NOT NULL,
   qzNum2 varchar2(100),
   qzNum3 varchar2(100),
   qzCorrectNum number,
   qzReward number,
   qzStartDate date,
   endDate date,
   qzRegDate date,
   qzModDate date,
   PRIMARY KEY (qzNo)
);

ALTER TABLE t_quiz_attend MODIFY qzno REFERENCES t_quiz(qzNo);

SELECT * FROM t_quiz WHERE enddate >= '24/03/28';
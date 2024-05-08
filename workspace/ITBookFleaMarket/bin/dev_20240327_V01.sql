SELECT * FROM t_account WHERE transactionType IN('입금', '출금') 
ORDER BY transactionNo DESC;

CREATE OR REPLACE TRIGGER  TRG_ACCOUNT_INSERT
AFTER
    INSERT ON t_account
    FOR EACH ROW
BEGIN
    UPDATE t_member SET purchasecnt = purchasecnt + 1 WHERE ID = 'aaa';
END;
/

ALTER TABLE t_book MODIFY bookName VARCHAR2(100);

ALTER TABLE t_book MODIFY author VARCHAR2(50);

ALTER TABLE t_book MODIFY publisher VARCHAR2(50);

ALTER TABLE t_book MODIFY bookState VARCHAR2(10);

ALTER TABLE t_book MODIFY bookGroup VARCHAR2(20);

ALTER TABLE t_tradelog MODIFY tradeState VARCHAR2(20);


ALTER TABLE t_roulette RENAME COLUMN prizename TO prizeMile;
ALTER TABLE t_roulette MODIFY prizeMile NUMBER;

ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C008644;
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C008645;

CREATE TABLE t_mileage_log
(
	mlLogNo number NOT NULL,
	userId varchar2(20) NOT NULL UNIQUE,
	mlLogType varchar2(20),
	mlAmount number,
	mlLogDate date,
	PRIMARY KEY (mlLogNo)
);

CREATE TABLE t_review
(
	reviewNo number NOT NULL,
	postNo number NOT NULL,
	rate number NOT NULL,
	reviewContent varchar2(1000),
	PRIMARY KEY (reviewNo)
);

ALTER TABLE t_mileage_log
	ADD FOREIGN KEY (userId)
	REFERENCES t_member (id)
;

ALTER TABLE t_review
	ADD FOREIGN KEY (postNo)
	REFERENCES t_tradePost (postNo)
;

ALTER TABLE t_tradeLog DROP COLUMN usedmileage;

ALTER TABLE t_tradeLog ADD mlLogNo NUMBER;

ALTER TABLE t_tradeLog
	ADD FOREIGN KEY (mlLogNo)
	REFERENCES t_mileage_log (mlLogNo)
;

ALTER TABLE t_review ADD reviewregdate DATE DEFAULT SYSDATE;
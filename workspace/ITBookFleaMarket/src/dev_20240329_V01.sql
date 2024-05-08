-- t_quiz_attend�� id unique ����
ALTER TABLE t_quiz_attend DROP CONSTRAINT SYS_C007175;
-- t_qna�� qid unique ����
ALTER TABLE t_qna DROP CONSTRAINT SYS_C007168;
-- t_qna�� answercontent ���� ����
ALTER TABLE t_qna MODIFY answercontent VARCHAR2(300);
-- t_tradelog�� tradeprice notnull ����
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007186;
-- t_tradelog�� tradedate notnull ����
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007187;
-- t_tradelog�� buyer notnull ����
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007189;
-- t_book�� seller notnull ����
ALTER TABLE t_book DROP CONSTRAINT SYS_C007156;
-- t_account�� transactionType ���� ����
ALTER TABLE t_account MODIFY transactionType VARCHAR2(10);
-- t_mileage_log�� id unique ����
ALTER TABLE t_mileage_log DROP CONSTRAINT SYS_C007217;
-- t_account�� trlogNo unique ����
ALTER TABLE t_account DROP CONSTRAINT SYS_C007236;

SELECT * FROM t_tradelog WHERE bookNo = 8;
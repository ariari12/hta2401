-- t_quiz_attend의 id unique 삭제
ALTER TABLE t_quiz_attend DROP CONSTRAINT SYS_C007175;
-- t_qna의 qid unique 삭제
ALTER TABLE t_qna DROP CONSTRAINT SYS_C007168;
-- t_qna의 answercontent 길이 변경
ALTER TABLE t_qna MODIFY answercontent VARCHAR2(300);
-- t_tradelog의 tradeprice notnull 삭제
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007186;
-- t_tradelog의 tradedate notnull 삭제
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007187;
-- t_tradelog의 buyer notnull 삭제
ALTER TABLE t_tradelog DROP CONSTRAINT SYS_C007189;
-- t_book의 seller notnull 삭제
ALTER TABLE t_book DROP CONSTRAINT SYS_C007156;
-- t_account의 transactionType 길이 변경
ALTER TABLE t_account MODIFY transactionType VARCHAR2(10);
-- t_mileage_log의 id unique 삭제
ALTER TABLE t_mileage_log DROP CONSTRAINT SYS_C007217;
-- t_account의 trlogNo unique 삭제
ALTER TABLE t_account DROP CONSTRAINT SYS_C007236;

SELECT * FROM t_tradelog WHERE bookNo = 8;
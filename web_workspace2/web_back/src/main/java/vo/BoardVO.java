package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
SQL> CREATE TABLE BOARD
2  (BNO NUMBER(8) CONSTRAINTS BOARD_BNO_PK PRIMARY KEY,
3  WRITER VARCHAR(20),
4  TITLE VARCHAR2(200),
5  CONTENTS CLOB,
6  REGDATE DATE,
7  HITS NUMBER(5),
8  IP CHAR(16),
9  STATUS NUMBER(3));
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private int bno;
	private String writer;
	private String title;
	private String contents;
	private String regdate;
	private int hits;
	private String ip;
	private int status;
}

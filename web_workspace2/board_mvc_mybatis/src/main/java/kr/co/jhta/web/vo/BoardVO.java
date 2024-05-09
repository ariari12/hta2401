package kr.co.jhta.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

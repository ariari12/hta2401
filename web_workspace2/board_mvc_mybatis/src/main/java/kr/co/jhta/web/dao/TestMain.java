package kr.co.jhta.web.dao;

import java.util.List;

import kr.co.jhta.web.vo.BoardVO;

public class TestMain {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		System.out.println("총 게시물수 : " + dao.getTotalCount());
		System.out.println("======================================");
		
		List<BoardVO> list = dao.selectAll(1, 20);
		list.stream().forEach(vo -> System.out.println(vo.getBno()+" : "+ vo.getTitle()));
	}
}

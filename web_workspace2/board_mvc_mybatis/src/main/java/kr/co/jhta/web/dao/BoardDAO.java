package kr.co.jhta.web.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jakarta.annotation.Resource;
import kr.co.jhta.web.vo.BoardVO;

public class BoardDAO {	
	public SqlSessionFactory factory;
	
	public BoardDAO() {
		//설계도
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			//건설 노동자
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			//공장
			factory = builder.build(r);
			//설계도 닫기
			r.close();
		} catch (IOException e) {
			System.out.println("config.xml 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
	public int getTotalCount() {
		SqlSession ss = factory.openSession(true); // openSession(true) : autocommit
		
		int count = ss.selectOne("kr.co.jhta.web.board.getTotal");	 // 별칭 : namespace명.id	
		ss.close();
		
		return count;
	}
	
	public List<BoardVO> selectAll(int startNo, int endNo){
		SqlSession ss = factory.openSession(true); // openSession(true) : autocommit
		
		//ss.selectList("별칭",전달 객체);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		
		List<BoardVO> list = 
				ss.selectList("kr.co.jhta.web.board.allSelectBoard",map);
		
		ss.close();
		return list;
	}
	
}

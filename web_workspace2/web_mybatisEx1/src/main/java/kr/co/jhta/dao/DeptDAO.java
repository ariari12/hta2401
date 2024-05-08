package kr.co.jhta.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.vo.DeptVO;

public class DeptDAO {
	private SqlSessionFactory factory;
	
	public DeptDAO() {
		try {
			// 1. 설계도
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			// 2. 건설노동자
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			// 3. 공장
			factory = builder.build(r);
			// 4. 설계도 닫기
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // constructor end
	
	// 전체조회
	public List<DeptVO> selectAll() {
		// 공장으로부터
		SqlSession ss = factory.openSession(true);
		List<DeptVO> list = ss.selectList("AllDept");
		ss.close();
		return list;
	}
	
	// 하나 추가
	public void addOne(DeptVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("addOne", vo);
		ss.close();
	}
}

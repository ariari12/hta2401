package kr.co.jhta.web.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.web.vo.DeptVO;

public class DeptDAO {
	private SqlSessionFactory factory;
	
	public DeptDAO() {
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 전체조회
	public List<DeptVO> selectAll() {
		SqlSession ss = factory.openSession(true);
		List<DeptVO> list = ss.selectList("queryDeptAll");
		ss.close();
		return list;
	}
	
	// 1건조회
	public DeptVO selectOne(int deptno) {
		SqlSession ss = factory.openSession(true);
		DeptVO vo = ss.selectOne("queryDeptOne", deptno);
		ss.close();
		return vo;
	}
	
	// 1건추가
	public void insertOne(DeptVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("insertDeptOne", vo);
		ss.close();
	}
	
	// 1건변경
	public void updateOne(DeptVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.update("updateDeptOne", vo);
		ss.close();
	}
	
	// 1건삭제
	public void deleteOne(int deptno) {
		SqlSession ss = factory.openSession(true);
		ss.delete("deleteDeptOne", deptno);
		ss.close();
	}
}

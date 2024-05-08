package kr.co.jhta.web.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.web.vo.EmpVO;

public class EmpDAO {
	// SqlSessionFactory 멤버변수
	private SqlSessionFactory factory;
	
	// 생성자에서 factory의 참조값을 할당
	public EmpDAO() {
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 전체조회
	public List<EmpVO> selectAll() {
		SqlSession ss = factory.openSession(true);
		List<EmpVO> list = ss.selectList("queryAllEmp");
		ss.close();
		return list;
	}
	
	// 1건조회
	public EmpVO getOne(int empno) {
		SqlSession ss = factory.openSession(true);
		EmpVO vo = ss.selectOne("queryEmpOne", empno);
		ss.close();
		return vo;
	}
	
	// 수정, 변경
	public void addOne(EmpVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("insertEmpOne", vo);
		ss.close();
	}
	
	// 삭제
	public void modifyOne(EmpVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.update("updateEmpOne", vo);
		ss.close();
	}
}

package javaz.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
	private String query;
	private PreparedStatement pstmt;
	private boolean result;
	private ResultSet rs;
	
	// t_emp 테이블에 데이터를 추가, 변경, 삭제, 조회
	public boolean insert(ExerciseVO evo) {
		query = " INSERT INTO t_emp VALUES(?, ?, ?, ?, SYSDATE) ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			// ?와 변수값 바인딩
			pstmt.setInt(1, evo.getEmpNo());
			pstmt.setString(2, evo.getEName());
			pstmt.setInt(3, evo.getDeptNo());
			pstmt.setInt(4, evo.getSal());
			
			result = pstmt.executeUpdate() == 1;
			pstmt.close();
			
			// 쿼리 실행 후 결과 받기
			// 반환 처리 - DML ONLY
			return result; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(ExerciseVO evo) {
		// 부서번호, 급여 수정
		query = " UPDATE t_emp SET sal = ?, deptno = ? WHERE empno = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, evo.getSal());
			pstmt.setInt(2, evo.getDeptNo());
			pstmt.setInt(3, evo.getEmpNo());
			
			result = pstmt.executeUpdate() == 1;
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean delete(int empNo) {
		query = " DELETE FROM t_emp WHERE empno = " + empNo + " ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			result = pstmt.executeUpdate() == 1;
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ExerciseVO select(int empNo) {
		query = " SELECT * FROM t_emp WHERE empno = " + empNo + " ";
		ExerciseVO evo = null;
		
		try {
			// 쿼리 실행 및 실행 결과 받기
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 조회되는 행이 존재하면
			if (rs.next()) {
//				System.out.println("----- 사원 정보 -----");
//				System.out.println("사원번호: " + rs.getInt("empno"));
//				System.out.println("사원이름: " + rs.getString("ename"));
//				System.out.println("부서번호: "+ rs.getInt("deptno"));
//				System.out.println("급여: " + rs.getInt("sal"));
//				System.out.println("입사일자: " + rs.getDate("hiredate"));
				
//				evo = new ExerciseVO(rs.getInt("empno"), rs.getString("ename"), 
//						rs.getInt("deptno"), rs.getInt("sal"), rs.getDate("hiredate"));
				
				evo = new ExerciseVO();
				evo.setEmpNo(rs.getInt("empno"));
				evo.setEName(rs.getString("ename"));
				evo.setDeptNo(rs.getInt("deptno"));
				evo.setSal(rs.getInt("sal"));
				evo.setHireDate(rs.getDate("hiredate"));
				
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return evo;
	}
	
	public List<ExerciseVO> select() {
		query = " SELECT * FROM t_emp ORDER BY empno ";
		ExerciseVO evo = null;
		List<ExerciseVO> evos = new ArrayList<ExerciseVO>();
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
//			System.out.println("사원번호\t사원이릅\t부서번호\t급여\t입사일자");
//			System.out.println("------------------------------------------");
			
			while (rs.next()) {
//				System.out.println(rs.getInt("empno") + "\t" + rs.getString("ename") + "\t" 
//						+ rs.getInt("deptno") + "\t" + rs.getInt("sal") + "\t" 
//						+ rs.getDate("hiredate"));
				
//				evos.add(new ExerciseVO(rs.getInt("empno"), rs.getString("ename"), 
//						rs.getInt("deptno"), rs.getInt("sal"), rs.getDate("hiredate")));
				
				evo = new ExerciseVO();
				evo.setEmpNo(rs.getInt("empno"));
				evo.setEName(rs.getString("ename"));
				evo.setDeptNo(rs.getInt("deptno"));
				evo.setSal(rs.getInt("sal"));
				evo.setHireDate(rs.getDate("hiredate"));
				evos.add(evo);
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return evos;
	}

	public static void main(String[] args) {
		Exercise exe = new Exercise();
		ExerciseVO evo = new ExerciseVO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			// 1. t_mep 테이블에 다음 레코드를 추가
			//    empno는 9002, ename은 Goo2, deptno는 99,
			//    sal는 9000, hiredate는 오늘로 지정
			// 값 입력받기
			System.out.println("----- INSERT -----");
			System.out.print("사원번호: ");
			evo.setEmpNo(Integer.parseInt(br.readLine().trim()));
			System.out.print("사원이름: ");
			evo.setEName(br.readLine().trim());
			System.out.print("부서번호: ");
			evo.setDeptNo(Integer.parseInt(br.readLine().trim()));
			System.out.print("급여: ");
			evo.setSal(Integer.parseInt(br.readLine().trim()));
			
			// insert 진행
			if (exe.insert(evo)) {
				System.out.println("INSERT SUCCEED");
			} else {
				System.out.println("INSERT FAILED");
			}
			
			// 2. t_emp 테이블의 전체 목록 조회
			List<ExerciseVO> evos = exe.select();
			
			System.out.println();
			if (!evos.isEmpty()) {
				System.out.println("사원번호\t사원이릅\t부서번호\t급여\t입사일자");
				System.out.println("------------------------------------------");
				
				evos.forEach(t -> 
				System.out.println(t.getEmpNo() + "\t" + t.getEName() + "\t" 
						+ t.getDeptNo() + "\t" + t.getSal() + "\t" 
						+ t.getHireDate()));
			} else {
				System.out.println("조회할 정보가 존재하지 않습니다.");
			}
			
			// 3. 1에서 추가한 레코드의 sal을 900으로, deptno는 90으로 변경
			// 값 입력받기
			System.out.println();
			System.out.println("----- UPDATE -----");
			System.out.print("사원번호: ");
			int empNo = Integer.parseInt(br.readLine().trim());
			
			if ( (evo = exe.select(empNo)) != null) {
				System.out.print("급여(변경 전: " + evo.getSal() + "): ");
				evo.setSal(Integer.parseInt(br.readLine().trim()));
				System.out.print("부서번호(변경 전: " + evo.getDeptNo() + "): ");
				evo.setDeptNo(Integer.parseInt(br.readLine().trim()));
				
				// update 진행
				if (exe.update(evo)) {
					System.out.println("UPDATE SUCCEED");
				} else {
					System.out.println("UPDATE FAILED");
				}
			} else {
				System.out.println("해당 사원번호를 가진 레코드가 존재하지 않습니다.");
			}
			
			// 4. t_emp 테이블의 empno가 9002인 레코드 조회
			
			// 값 입력받기
			System.out.println();
			System.out.println("----- SELECT SPECIFIC -----");
			System.out.print("사원번호: ");
			empNo = (Integer.parseInt(br.readLine().trim()));
			evo = exe.select(empNo);
			
			// 조회 값 출력
			if (evo != null) {
				System.out.println("----- 사원 정보 -----");
				System.out.println("사원번호: " + evo.getEmpNo());
				System.out.println("사원이름: " + evo.getEName());
				System.out.println("부서번호: "+ evo.getDeptNo());
				System.out.println("급여: " + evo.getSal());
				System.out.println("입사일자: " + evo.getHireDate());
			} else {
				System.out.println("조회할 정보가 존재하지 않습니다.");
			}
			
			// 5. t_emp 테이블의 empno가 9002인 레코드 삭제
			// 값 입력받기
			System.out.println();
			System.out.println("----- DELETE -----");
			System.out.print("사원번호: ");
			empNo = (Integer.parseInt(br.readLine().trim()));
			
			if ( (evo = exe.select(empNo)) != null) {
				System.out.print("정말로 삭제하시겠습니까?(y): ");
				
				// delete 진행
				if (br.readLine().trim().equalsIgnoreCase("y")) {
					if (exe.delete(empNo)) {
						System.out.println("DELETE SUCCEED");
					} else {
						System.out.println("DELETE FAILED");
					}
				} else {
					System.out.println("삭제를 취소했습니다.");
				}
			} else {
				System.out.println("해당 사원번호를 가진 레코드가 존재하지 않습니다.");
			}
			
			// 6. t_emp 테이블의 전체 목록 조회
			evos = exe.select();
			
			System.out.println();
			if (!evos.isEmpty()) {
				System.out.println("사원번호\t사원이릅\t부서번호\t급여\t입사일자");
				System.out.println("------------------------------------------");
				
				evos.forEach(t -> 
				System.out.println(t.getEmpNo() + "\t" + t.getEName() + "\t" 
						+ t.getDeptNo() + "\t" + t.getSal() + "\t" 
						+ t.getHireDate()));
			} else {
				System.out.println("조회할 정보가 존재하지 않습니다.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package javaz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

// JDBC; Java DataBase Connectivity
// - 자바 어플리케이션과 데이터베이스를 연결하는 라이브러리
// - 자바 프로그램에서 DB에 접근하여 검색 및 저장, 변경, 삭제 등이 가능
// - 2계층과 3계층 처리 모델 모두 지원

// 2계층 Two-tier
// - 클라이언트/서버 구성
// - 자바 어플리케이션이 직접 DB 서버와 연결
// - JDBC 드라이버가 데이터소스와 직접 통신
// - 어플리케이션 로직은 사용자 인터페이스 내부 또는 서버의 DB에 위치

// 3계층 Three-tier
// - 어플리케이션 로직이 데이터 및 사용자 인터페이스와 분리되어
//   중간 계층에 위치
//   트랜잭션 처리 모니터, 메시지 서버, 응용 프로그램 서버 등
// - 중간 계층을 이용하여 사용자의 접근과 비즈니스 데이터 업데이트 제어 가능
// - 웹 기반 시스템

public class CRUDTest {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";
		
		try {
			Class.forName(driver);	// 드라이버 로딩
			System.out.println("드라이버 로딩 ok");
			
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("con ok");
			
			Statement stmt = con.createStatement(); // 쿼리문에 변수가 없을 때
			
			System.out.println("stmt ok");
			
			int result = -1;
			String query = "";
			ResultSet rs;
			
			// 1. t_emp 테이블에 레코드를 추가하는 쿼리문 작성 
			//    empno는 7777, ename은 Chil, deptno는 77, 
			//    sal는 7000, hiredate는 오늘로 지정
//			query = " INSERT INTO t_emp VALUES(7777, 'Chil', 77, 7000, SYSDATE) ";
//			
//			// INSERT 쿼리 실행
//			result = stmt.executeUpdate(query);
//			if (result == 1) {
//				System.out.println("7777 레코드 추가 성공");
//			} else {
//				System.out.println("7777 레코드 추가 실패");
//			}
//			
//			// 2. t_emp 테이블에 레코드를 추가하는 쿼리문 작성 
//			//    empno는 88, ename은 Pal, deptno는 88, 
//			//    sal는 8000, hiredate는 어제로 지정
//			query = " INSERT INTO t_emp VALUES(88, 'Pal', 88, 8000, SYSDATE - 1) ";
//			result = stmt.executeUpdate(query);
//			if (result == 1) {
//				System.out.println("88 레코드 추가 성공");
//			} else {
//				System.out.println("88 레코드 추가 실패");
//			}
//			
//			// 3. t_emp 테이블에 레코드를 변경하는 쿼리문 작성
//			//    empno가 88인 레코드의 sal은 700, deptno는 77
//			query = " UPDATE t_emp SET sal = 700, deptno = 77 WHERE empno = 88 ";
//			result = stmt.executeUpdate(query);
//			if (result == 1) {
//				System.out.println("88 레코드 업데이트 성공");
//			} else {
//				System.out.println("88 레코드 업데이트 실패");
//			}
//			
//			// 4. t_emp 테이블에 empno가 88인 레코드를 삭제하는 쿼리문 작성
//			query = " DELETE FROM t_emp WHERE empno = 88 ";
//			result = stmt.executeUpdate(query);
//			if (result == 1) {
//				System.out.println("88 레코드 삭제 성공");
//			} else {
//				System.out.println("88 레코드 삭제 실패");
//			}
			
//			query = " DELETE FROM t_emp WHERE empno = 7777 ";
//			result = stmt.executeUpdate(query);
//			if (result == 1) {
//				System.out.println("7777 레코드 삭제 성공");
//			} else {
//				System.out.println("7777 레코드 삭제 실패");
//			}
			
			// 7. 입력받은 값을 이용하여 레코드 추가 쿼리문 작성
			int empno = 9001;
			String ename = "Goo";
			int deptno = 99;
			int sal = 900;
			
			query = " INSERT INTO t_emp VALUES(" 
					+ empno + ", '" + ename + "', "
					+ deptno + ", " + sal + ", SYSDATE) ";
			
			query = " INSERT INTO t_emp(empno, ename, deptno, sal, hiredate) " 
							  + "VALUES(?,      ?,      ?,     ?, SYSDATE) ";
			
			// 쿼리문에 변수가 있을 때
			PreparedStatement pstmt = con.prepareStatement(query);
			// ?와 변수값 바인딩
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setInt(3, deptno);
			pstmt.setInt(4, sal);
			
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("레코드 추가 성공");
			} else {
				System.out.println("레코드 추가 실패");
			}
			
			// 5. t_emp 테이블의 모든 레코드를 조회하는 쿼리문 작성
			//    단, deptno가 작은 값부터 조회되도록 처리
			query = " SELECT * FROM t_emp ORDER BY deptno ";
			
			// SELECT 쿼리 실행 결과를 rs 객체에 저장
			rs = stmt.executeQuery(query);
			System.out.println("사원번호\t사원이릅\t부서번호\t급여\t입사일자\t입사일자(시분초)");
			System.out.println("-------------------------------------------------------------------");
			while (rs.next()) {  // 다음 값이 없을 때까지 반복하여
				// 각 컬럼의 값을 읽어와서 화면에 표시
				System.out.println(rs.getInt("empno") + "\t" + rs.getString("ename") + "\t"
							+ rs.getInt("deptno") + "\t" + rs.getInt("sal") + "\t"
							+ rs.getDate("hiredate") + "\t" + rs.getString("hiredate"));
				
			}
			rs.close();
			
			System.out.println();
			
			// 6. t_emp empno가 7777인 레코드를 조회하는 쿼리문 작성
//			query = " SELECT * FROM t_emp WHERE empno = 7777 ";
//			rs = stmt.executeQuery(query);
//			// 컬럼의 값을 읽어와서 화면에 표시
//			if (rs.next()) {
//				System.out.println("----- 사원 정보 -----");
//				System.out.println("사원번호: " + rs.getInt("empno"));
//				System.out.println("사원이름: " + rs.getString("ename"));
//				System.out.println("부서번호: "+ rs.getInt("deptno"));
//				System.out.println("급여: " + rs.getInt("sal"));
//				System.out.println("입사일자: " + rs.getDate("hiredate"));
//			}
//			System.out.println();
			
			System.out.println("DB 연결 ok!");
			
			pstmt.close();
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.err.println(">> 드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.err.println(">> DB 연결 실패 또는 SQLException 예외 발생");
			e.printStackTrace();
		}
	}
}

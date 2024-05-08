package javaz.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureMain {

	public static void main(String[] args) throws SQLException {
		String query = "{call EMP_BONUS(?)}";
		
		CallableStatement cstmt = DBConn.getConnection().prepareCall(query);
		cstmt.registerOutParameter(1, Types.DOUBLE);
		cstmt.execute(); // 쿼리 실행
		System.out.println("보너스 총액: " + cstmt.getDouble(1));
	}

}

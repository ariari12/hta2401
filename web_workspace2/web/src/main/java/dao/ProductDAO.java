package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import vo.ProductVO;

public class ProductDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "scott";
	private String password = "tiger";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	StringBuffer sb =  new StringBuffer();
	
	public ProductDAO() {
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	// 전체조회: selectAll()
	public ArrayList<ProductVO> selectAll() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		sb.setLength(0);
		sb.append(" SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 1건조회: getOne(int pno);
	public ProductVO getOne(int pno) {
		ProductVO vo = null;
		sb.setLength(0);
		sb.append(" SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product ");
		sb.append(" WHERE pno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, pno);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	// findByName(String pname) 상품명으로 조회
	// like 연산자 포함해서
	public ArrayList<ProductVO> findByName(String p) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		sb.setLength(0);
		sb.append(" SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product ");
		sb.append(" WHERE pname LIKE ? ");
		
		if (p.equals("")) {
			return null; // 혹은 p를 존재하지 않을 법한 상품명으로 세팅
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, "%" + p + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<ProductVO> getData(Set<Integer> key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		sb.setLength(0);
		sb.append(" SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile ");
		sb.append(" FROM product WHERE pno IN (  ");
		
		try {
			
			for (Integer it : key) {
				sb.append(it + ", ");
			}
			sb.delete(sb.length() - 2, sb.length() - 1);
			sb.append(" )");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			System.out.println("query: " + sb);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) { pstmt.close(); }
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public ArrayList<ProductVO> getData1(Set<Integer> key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		sb.setLength(0);
		sb.append(" SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile ");
		sb.append(" FROM product WHERE pno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			for (Integer pno : key) {
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();
				rs.next();
				
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) { pstmt.close(); }
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}

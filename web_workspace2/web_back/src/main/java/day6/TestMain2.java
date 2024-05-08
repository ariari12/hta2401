package day6;

import java.util.ArrayList;
import java.util.HashSet;

import dao.ProductDAO;
import vo.ProductVO;

public class TestMain2 {
	
	public static void main(String[] args) {
//		int cnt = 0;
//		
//		for(int i = 1; i <= 4; i++) {
//			for(int j = 1; j <= 5; j++) {
//				cnt++;
//				if(cnt == 10) break;
//				System.out.println("i = " + i + ", j = " + j);
//			}
//		}
		
		ProductDAO dao = new ProductDAO();
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(3);
		hs.add(3);
		hs.add(11);
		
		ArrayList<ProductVO> list = dao.getData1(hs);
		System.out.println("사이즈: " + list.size());
		System.out.println("list: " + list);
	}
}

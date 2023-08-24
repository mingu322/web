package model;

import java.util.ArrayList;

public class DAOTest {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		System.out.println("데이터 검색 수: " + dao.total("냉장고"));
		
//		ArrayList<ProductVO> array = dao.list(1, "냉장고");
//		for(ProductVO vo:array) {
//			System.out.println(vo.toString()); }
		}

	}
 

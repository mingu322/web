package model;

import java.sql.*;
import java.util.*;

public class ProductDAO {
	//상품목록출력
	public List<ProductVO> list(){
		List<ProductVO> array = new ArrayList<>();
		try {
			String sql = "select * from products order by code desc";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setCode(rs.getInt("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setRdate(rs.getTimestamp("rdate"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("상품 목록 출력 오류" + e.toString());
		}
		return array;
	}
}

package model;

import java.util.*;
import java.sql.*;

public class LocalDAO {
	public ArrayList<LocalVO> list(int page, String query){
		ArrayList<LocalVO> array = new ArrayList<LocalVO>();
		try {
			String sql = "select * from local where lname like ? or laddress like ?";
			sql += "order by id desc limit ?,5";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ps.setString(2, "%" + query + "%");
			ps.setInt(3, (page-1) * 5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocalVO vo = new LocalVO();
				vo.setId(rs.getInt("id"));
				vo.setLid(rs.getString("lid"));
				vo.setLname(rs.getString("lname"));
				vo.setLaddress(rs.getString("laddress"));
				vo.setLphone(rs.getString("lphone"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("지역목록:" + e.toString());
		}
		return array;
	}
}

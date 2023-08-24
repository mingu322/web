package model;

import java.sql.*;
import java.util.*;

public class StuDAO {
	//수강취소
	public void delete(String scode, String lcode) {
		try {
			String sql = "call del_enroll(?,?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, scode);
			cs.setString(2, lcode);
			cs.execute();
		}catch(Exception e) {
			System.out.println("수강신청취소: " + e.toString());
		}
	}
	//수강신청등록
	public int insert(String scode, String lcode) {
		int count=-1;
		try {
			String sql = "call add_enroll(?,?,?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, scode);
			cs.setString(2, lcode);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.execute();
			count = cs.getInt(3);
		}catch(Exception e) {
			System.out.println("수강신청등록: " + e.toString());
		}
		return count;
	}
	//수강신청목록
	public ArrayList<EnrollVO> list(String scode){
		ArrayList<EnrollVO> array = new ArrayList<EnrollVO>();
		try{
			String spl = "select * from view_enroll_cou where scode=?";
			PreparedStatement ps =Database.CON.prepareStatement(spl);
			ps.setString(1, scode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EnrollVO vo = new EnrollVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setEdate(rs.getString("edate"));
				vo.setGrade(rs.getInt("grade"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPersons(rs.getInt("persons"));
				vo.setRoom(rs.getString("room"));
				vo.setHours(rs.getInt("hours"));
				vo.setPname(rs.getString("pname"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("수강신청목록: " + e.toString());
		}
		return array;
	}
	//학생수정
	public void update(StuVO vo) {
		try {
			String sql = "update students set sname=?,dept=?,year=?,birthday=?,advisor=? where scode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);	
			ps.setString(1, vo.getSname());
			ps.setString(2, vo.getDept());
			ps.setInt(3, vo.getYear());
			ps.setString(4, vo.getBirthday());
			ps.setString(5, vo.getAdvisor());
			ps.setString(6, vo.getScode());
			ps.execute();
		}catch(Exception e) {
			System.out.println("학생수정: " + e.toString());
		}
	}
	//학생정보
	public StuVO read(String scode	) {
		StuVO vo = new StuVO();
		try {
			String sql = "select * from view_stu where scode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, scode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setDept(rs.getString("dept"));
				vo.setYear(rs.getInt("year"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setAdvisor(rs.getString("advisor"));
				vo.setPname(rs.getString("pname"));
			}
			
			}catch(Exception e) {
				System.out.println("학생정보: " + e.toString());
			}
		return vo;
	}
	//학생정보수정
	public void insert(StuVO vo) {
		try {
			String ncode = "";
			String sql = "select max(scode) +1 ncode from students";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) ncode = rs.getString("ncode");
			sql = "insert into students(scode,sname,dept,year,birthday,advisor) values(?,?,?,?,?,?)";
			ps = Database.CON.prepareStatement(sql);	
			ps.setString(1, ncode);
			ps.setString(2, vo.getSname());
			ps.setString(3, vo.getDept());
			ps.setInt(4, vo.getYear());
			ps.setString(5, vo.getBirthday());
			ps.setString(6, vo.getAdvisor());
			ps.execute();
		}catch(Exception e) {
			System.out.println("학생정보수정: " + e.toString());
		}
	}
	//학생 수
	public int total(String query, String key) {
		int total = 0;
		try {
			String sql = "select count(*) cnt from view_stu where " + key + " like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt("cnt");
		}
			}catch(Exception e) {
			System.out.println("학생목록: " + e.toString());
			}
			return total;
	}
	public ArrayList<StuVO> list(int page, String query, String key){
		ArrayList<StuVO> array = new ArrayList<StuVO>();
		try {
		String sql = "select * from view_stu where " + key + " like ? limit ?,5";
		PreparedStatement ps = Database.CON.prepareStatement(sql);
		ps.setString(1, "%" + query + "%");
		ps.setInt(2, (page-1)*5);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StuVO vo = new StuVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			vo.setYear(rs.getInt("year"));
			vo.setBirthday(rs.getString("birthday"));
			vo.setAdvisor(rs.getString("advisor"));
			vo.setPname(rs.getString("pname"));
			array.add(vo);
		}
		
		}catch(Exception e) {
			System.out.println("학생목록: " + e.toString());
		}
		return array;
	}
}

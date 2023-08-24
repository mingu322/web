package model;

import java.sql.*;
import java.util.*;

public class CouDAO {
	//점수수정
	public void update(GradeVO vo) {
		try {
			String sql ="update enrollments set grade=? where scode=? and lcode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, vo.getGrade());
			ps.setString(2, vo.getScode());
			ps.setString(3, vo.getLcode());
			ps.execute();
		}catch(Exception e) {
			System.out.println("점수 수정: " + e.toString());
		}
	}
	//성적목록
	public ArrayList<GradeVO> list(String lcode){
		ArrayList<GradeVO> array = new ArrayList<GradeVO>();
		try {
			String sql = "select * from view_enroll_stu where lcode=? order by scode";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, lcode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GradeVO vo = new GradeVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setEdate(rs.getString("edate"));
				vo.setGrade(rs.getInt("grade"));
				vo.setDept(rs.getString("dept"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("성적목록: " + e.toString());
		}
		return array;
	}
	//강의수정
	public void update(CourseVO vo) {
		try {
			String sql ="update courses set lname=?,room=?,hours=?,capacity=?,persons=?,instructor=? where lcode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getLname());
			ps.setString(2, vo.getRoom());
			ps.setInt(3, vo.getHours());
			ps.setInt(4, vo.getCapacity());
			ps.setInt(5, vo.getPersons());
			ps.setString(6, vo.getInstructor());
			ps.setString(7, vo.getLcode());
			ps.execute();
		}catch(Exception e) {
			System.out.println("강좌 수정: " + e.toString());
		}
	}
	//강의정보
	public CourseVO read(String lcode) {
		CourseVO vo = new CourseVO();
		try {
			String sql = "select * from view_cou where lcode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, lcode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setRoom(rs.getString("room"));
				vo.setInstructor(rs.getString("instructor"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPersons(rs.getInt("persons"));
				vo.setPname(rs.getString("pname"));
			}
		}catch(Exception e) {
			System.out.println("강좌정보: " + e.toString());
		}
		return vo;
	}
	public String getCode() {
		String code = "";
		try {
			String mcode = "";
			String sql ="select max(lcode) mcode from courses";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) mcode=rs.getString("mcode");
			code = "N" + (Integer.parseInt(mcode.substring(1))+1);
		}catch(Exception e) {
			System.out.println("강좌등록: " + e.toString());
		}
		return code;
	}
	
	//강좌등록
	public void insert(CourseVO vo) {
		try {
			String code =getCode();
			String sql ="insert into courses(lcode,lname,room,hours,capacity,persons,instructor) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, vo.getLname());
			ps.setString(3, vo.getRoom());
			ps.setInt(4, vo.getHours());
			ps.setInt(5, vo.getCapacity());
			ps.setInt(6, vo.getPersons());
			ps.setString(7, vo.getInstructor());
			ps.execute();
		}catch(Exception e) {
			System.out.println("강좌 등록: " + e.toString());
		}
	}
	//강좌 검색 수
	public int total(String key, String query) {
		int total = 0;
		try {
			String sql = "select count(*) cnt from view_cou where " + key + " like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("강좌 검색 수: " + e.toString());
		}
		return total;
	}
	//강좌선택목록
	public ArrayList<CourseVO> all(){
		ArrayList<CourseVO> array = new ArrayList<>();
		try {
			String sql = "select * from view_cou order by lname";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CourseVO vo = new CourseVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setRoom(rs.getString("room"));
				vo.setInstructor(rs.getString("instructor"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPersons(rs.getInt("persons"));
				vo.setPname(rs.getString("pname"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("강좌목록: " + e.toString());
		}
		return array;
	}
	//강좌목록
	public ArrayList<CourseVO> list(int page, String key, String query){
		ArrayList<CourseVO> array = new ArrayList<>();
		try {
			String sql = "select * from view_cou where " + key + " like ? limit ?,5";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ps.setInt(2, (page-1)*5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CourseVO vo = new CourseVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setRoom(rs.getString("room"));
				vo.setInstructor(rs.getString("instructor"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPersons(rs.getInt("persons"));
				vo.setPname(rs.getString("pname"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("강좌목록: " + e.toString());
		}
		return array;
	}
}

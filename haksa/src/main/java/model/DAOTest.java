package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;

public class DAOTest {

	public static void main(String[] args) {
		StuDAO dao = new StuDAO();
		ArrayList<EnrollVO> array = dao.list("92514023");
		for(EnrollVO vo:array) {
			System.out.println(vo.toString());
		}
	}
}

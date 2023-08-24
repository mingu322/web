package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.LocalDAO;
import model.LocalVO;

@WebServlet(value = {"/local/list", "/local/list.json", "/local/total"})
public class LocalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LocalDAO dao = new LocalDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		switch(request.getServletPath()) {
		case "/local/list":
			request.setAttribute("pageName", "/local/list.jsp");
			dis.forward(request, response);
			break;
		case "/local/list.json":
			int page = Integer.parseInt(request.getParameter("page"));
			String query = request.getParameter("query");
			ArrayList<LocalVO> array = dao.list(page, query);
			JSONArray jArray = new JSONArray();
			for(LocalVO vo:array) {
				JSONObject obj = new JSONObject();
				obj.put("id", vo.getId());
				obj.put("lid", vo.getLid());
				obj.put("lname", vo.getLname());
				obj.put("lphone", vo.getLphone());
				obj.put("laddress", vo.getLaddress());
				jArray.add(obj);
			}
			out.print(jArray);
			break;
		case "/local/total":
			
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

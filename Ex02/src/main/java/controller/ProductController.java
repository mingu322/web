package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet(value= {"/pro/list", "/pro/insert", "/pro/read", "/pro/delete", "/pro/update"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductDAO dao = new ProductDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		switch(request.getServletPath()) {
		case "/pro/list":
			int page = request.getParameter("page")==null ? 1:
				Integer.parseInt(request.getParameter("page"));
			request.setAttribute("page", page);
			request.setAttribute("array", dao.list(page));
			request.setAttribute("pageName", "/pro/list.jsp");
			dis.forward(request, response);
			break; 
			
		case "/pro/insert":
			request.setAttribute("pageName", "/pro/insert.jsp");
			dis.forward(request, response);
			break;
			
		case "/pro/read":
			String code = request.getParameter("code");
//			System.out.println("code.........." + code);
			request.setAttribute("vo", dao.read(code));
			request.setAttribute("pageName", "/pro/read.jsp");
			dis.forward(request, response);
			break;
			
		case "/pro/delete":
			code = request.getParameter("code");
//			System.out.println("delete code.........." + code);
			dao.delete(code);
			response.sendRedirect("/pro/list");
			break;
		
		case "/pro/update":
			code = request.getParameter("code");
			request.setAttribute("vo", dao.read(code));
			request.setAttribute("pageName", "/pro/update.jsp");
			dis.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		switch(request.getServletPath()) {
		case "/pro/insert":
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			ProductVO vo = new ProductVO();
			vo.setName(name);
			vo.setPrice(Integer.parseInt(price));
			System.out.println(vo.toString());
			dao.insert(vo);
			response.sendRedirect("/pro/list");
			break;
		
		case "/pro/update":
			String code = request.getParameter("code");
			name = request.getParameter("name");
			price = request.getParameter("price");
			vo=new ProductVO();
			vo.setCode(Integer.parseInt(code));
			vo.setName(name);
			vo.setPrice(Integer.parseInt(price));
//			System.out.println(vo.toString());
			dao.update(vo);
			response.sendRedirect("/pro/read?code=" + code);
			break;
		}
	}

}

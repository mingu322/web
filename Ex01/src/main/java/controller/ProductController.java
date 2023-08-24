package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet(value={"/pro/list2", "/pro/insert"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductDAO dao = new ProductDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(request.getServletPath()) {
		case "/pro/list2" : 
			request.setAttribute("array", dao.list());
			request.setAttribute("pageName", "/pro/list2.jsp");
			break;
		case "/pro/insert" :
			request.setAttribute("pageName", "/pro/insert.jsp");
			break;
		}
		RequestDispatcher dis = request.getRequestDispatcher("/Home.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

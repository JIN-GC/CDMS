package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.lm.UserUtilsDAO;
import model.vm.Users;

@WebServlet("/Redirect")
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String redirect_page = request.getParameter("redirect_page");
		if (!(user_name == null)) {
			Users user = (Users)request.getAttribute("loginAuth");
			request.setAttribute("loginAuth", user);
			if( user.isLogin(user) ) {
				request.setAttribute("loginAuth", user);
			}
		}
		System.out.println("[Redirect][doPost][user_name:" + user_name + ", redirect_page:" + redirect_page + " ]");
		if (!(redirect_page == null || redirect_page.equals("") || "".equals(redirect_page) || redirect_page.contains("null"))) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirect_page);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}

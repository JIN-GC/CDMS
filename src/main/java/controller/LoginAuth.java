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

@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/loginAuth.jsp");
//		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String user_pass = request.getParameter("user_pass");
		String redirect_page = request.getParameter("redirect_page");
		if (redirect_page == null || redirect_page.equals("") || "".equals(redirect_page) || redirect_page.contains("null")) {
			redirect_page = "/WEB-INF/view/";
		}
		System.out.println("[SearchContents][doPost][user_name:" + user_name + ", user_pass:" + user_pass + " ]");
		
		//	impliments user instance 
		Users user = new Users(user_name, user_pass, redirect_page);
		
		//	LoginLogic & CheckUserAccount
		//	CheckUserAccount cua = new CheckUserAccount();
		//	boolean isLogin = cua.c	heckAuth(user);
		//	System.out.println("[isLogin]" + isLogin);
		UserUtilsDAO uud = new UserUtilsDAO();
		user = uud.findUser(user);
		request.setAttribute("loginAuth", user);
		
		System.out.println("[LoginAuth][doPost][isLogin]" + user.isLogin(user));
		
		//	if( isLogin ) {
		if( user.isLogin(user) ) {
			HttpSession hs = request.getSession();
			hs.setAttribute("loginAuth", user);
			request.setAttribute("loginAuth", user);
			
			hs.setAttribute("user_name", user.getUser_Name());
			hs.setAttribute("user_fg", user.getUser_Fg());
			hs.setAttribute("user_roll_cd", user.getUser_Roll_Cd());
			hs.setAttribute("user_status_cd", user.getUser_Status_Cd());
			
			//	forward to display login status
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/loginAuth.jsp");
			dispatcher.forward(request, response);
			
		} else {
			//	forward to return JSP page
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/loginFailed.jsp");
			dispatcher.forward(request, response);
		}
	}

}

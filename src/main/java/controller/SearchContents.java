package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.lm.ContentUtilsDAO;
import model.vm.Contents;
import model.vm.Users;


@WebServlet("/SearchContents")
public class SearchContents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	public void init(ServletConfig config) throws ServletException {
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/searchContentsList.jsp");
//		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int contents_id = 0;
		int contents_type_cd = 0;
		int contents_status_cd = 0;
		int select_page = 0;
		int searched_pages = 0;
		String c_id = request.getParameter("contents_id");
		String c_t_cd = request.getParameter("contents_type_cd");
		String c_s_cd = request.getParameter("contents_status_cd");
		String contents_title = request.getParameter("contents_title");
		String contents_data = request.getParameter("contents_data");
		String contents_created_at = null;
		String contents_updated_at = null;
		String contents_cancelled_at = null;
		String contents_closed_at = null;
		String contents_updated_history = null;
		String search_key = request.getParameter("search_key");
		String s_p = request.getParameter("select_page");
		String s_ps = request.getParameter("searched_pages");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String contents_created_start_at = request.getParameter("contents_created_start_at");
		String contents_created_end_at = request.getParameter("contents_created_end_at");
		
		if (!(c_id == null || c_id.equals("") || "".equals(c_id)) ) contents_id = Integer.parseInt(c_id);
		if (!(c_t_cd == null || "".equals(c_t_cd) || c_t_cd.equals("")) ) contents_type_cd = Integer.parseInt(c_t_cd);
		if (!(c_s_cd == null || "".equals(c_s_cd) || c_s_cd.equals("")) ) contents_status_cd = Integer.parseInt(c_s_cd);
		if (!(contents_created_start_at == null || "".equals(contents_created_start_at) || contents_created_start_at.equals("")) ) {
			contents_created_start_at = dateFormat.format(request.getParameter("contents_created_start_at"));
		} else {
			contents_created_start_at = null;
		}
		if (!(contents_created_end_at == null || "".equals(contents_created_end_at) || contents_created_end_at.equals("")) ) {
			contents_created_end_at = dateFormat.format(request.getParameter("contents_created_end_at"));
		} else {
			contents_created_end_at = null;
		}
		if (!(s_p == null || s_p.equals("") || "".equals(s_p)) ) select_page = Integer.parseInt(s_p);
		if (!(s_ps == null || s_ps.equals("") || "".equals(s_ps)) ) searched_pages = Integer.parseInt(s_ps);
		
		System.out.println("[SearchContents][doPost][contents_id: " + contents_id + ", c_id: " + c_id + "]");
		System.out.println("[SearchContents][doPost][contents_type_cd: " + contents_type_cd + "c_t_cd: " + c_t_cd + "]");
		System.out.println("[SearchContents][doPost][contents_status_cd: " + contents_status_cd + "c_s_cd: " + c_s_cd + "]");
		System.out.println("[SearchContents][doPost][contents_title: " + contents_title + "]");
		System.out.println("[SearchContents][doPost][contents_data: " + contents_data + "]");
		System.out.println("[SearchContents][doPost][search_key: " + search_key + "]");
		System.out.println("[SearchContents][doPost][select_page: " + select_page + "]");
		System.out.println("[SearchContents][doPost][searched_pages: " + searched_pages + "]");
		System.out.println("[SearchContents][doPost][contents_created_start_at: " + contents_created_start_at + "]");
		System.out.println("[SearchContents][doPost][contents_created_end_at: " + contents_created_end_at + "]");
		//	削除予定項目　search_key
		
		ContentUtilsDAO cudao = new ContentUtilsDAO();
		List<Contents> SerchDataList = cudao.SearchData(contents_id, contents_type_cd, contents_status_cd, contents_title, contents_data, contents_created_at, contents_updated_at, contents_cancelled_at, contents_closed_at, contents_updated_history, contents_created_start_at, contents_created_end_at); 
		request.setAttribute("SerchDataList", SerchDataList);
		//	impliments user instance 
		HttpSession hs = request.getSession();
		Users user = (Users)hs.getAttribute("loginAuth");
		//	Users user = (Users)request.getAttribute("loginAuth");
		
		System.out.println("[SearchContents][doPost][user_name:" + user.getUser_Name() + ", user_pass:" + user.getUser_Pass() + " ]");
		if( user.isLogin(user) ) {
			user.setUser_Status_Cd(11);
			//	HttpSession hs = request.getSession();
			request.setAttribute("loginAuth", user);
			Contents SerchRequestData = new Contents(search_key, select_page, searched_pages, contents_id, contents_type_cd, contents_status_cd, contents_title,
					contents_data, contents_created_start_at, contents_created_end_at); 
			request.setAttribute("select_page", select_page);
			request.setAttribute("searched_pages", searched_pages);
			request.setAttribute("contents_id", contents_id);
			request.setAttribute("contents_type_cd", contents_type_cd);
			request.setAttribute("contents_status_cd", contents_status_cd);
			request.setAttribute("contents_title", contents_title);
			request.setAttribute("contents_data", contents_data);
			request.setAttribute("contents_created_start_at", contents_created_start_at);
			request.setAttribute("contents_created_end_at", contents_created_end_at);
			request.setAttribute("SerchRequestData", SerchRequestData);
			//	検索結果画面に遷移（フォワード）
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/searchList.jsp");
			dispatcher.forward(request, response);
		} else {
			user.setUser_Status_Cd(1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			dispatcher.forward(request, response);
		}		
	}
}

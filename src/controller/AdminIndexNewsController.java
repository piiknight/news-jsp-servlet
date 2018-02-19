package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.News;
import model.dao.NewsDAO;
import util.AuthUtil;


public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
       
    public AdminIndexNewsController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int sumNews = newsDAO.countNews();
		int sumPage = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_ADMIN_NEWS);
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/news?error=2");
				return;
			}
		}
		int offset = (currentPage - 1) * Define.ROW_COUNT_ADMIN_NEWS;
		
		
		ArrayList<News> listNews = newsDAO.getItemsPagination(offset);
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listNews", listNews);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/news/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;

public class PublicIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
       
    public PublicIndexNewsController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("news", newsDAO.getItemsPagination(offset));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/public/tintuc.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

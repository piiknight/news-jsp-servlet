package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;


public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
	private CategoryDAO categoryDAO;
       
    public PublicIndexCatController() {
        super();
        newsDAO = new NewsDAO();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		Category category = categoryDAO.getItem(id);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		int sumNews = newsDAO.countNews(id);
		int sumPage = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_ADMIN_NEWS);
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
				return;
			}
		}
		int offset = (currentPage - 1) * Define.ROW_COUNT_ADMIN_NEWS;

		request.setAttribute("category", category);
		
		request.setAttribute("listNews", newsDAO.getItemsPagination(id, offset));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("sumPage", sumPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/public/danhmuc.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

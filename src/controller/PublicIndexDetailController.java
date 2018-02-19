package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import constant.Define;
import model.bean.News;
import model.dao.NewsDAO;

public class PublicIndexDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
       
    public PublicIndexDetailController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		News news = newsDAO.getItem(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		int sumNews = newsDAO.countNews(news.getCategory().getId()) - 1;
		int sumPageInvolve = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_NEWS_INVOLVE);
		
		request.setAttribute("news", news);
		request.setAttribute("sumPageInvolve", sumPageInvolve);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/public/chitiet.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idpage = 0;
		try {
			idpage = Integer.parseInt(request.getParameter("idpage"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		
		News news = newsDAO.getItem(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/index?error=1");
			return;
		}
		int offset = (idpage - 1) * Define.ROW_COUNT_NEWS_INVOLVE;
		
		ArrayList<News> listInvolve = newsDAO.getItemsPaginationInvolve(news.getCategory().getId(), id, offset);
		
		request.setAttribute("listInvolve", listInvolve);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/public/involve.jsp");
		rd.forward(request, response);
		return;
	}

}

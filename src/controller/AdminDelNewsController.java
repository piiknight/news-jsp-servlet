package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewsDAO;
import util.AuthUtil;


public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
       
    public AdminDelNewsController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
			return;
		}
		
		News news = newsDAO.getItem(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
			return;
		}
		
		if (newsDAO.delItem(id) > 0	) {
			String dirPath = request.getServletContext().getRealPath("/files");
			String fileName = news.getPicture();
			String filePath = dirPath + File.separator + fileName;
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			// xóa thành công
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		} else {
			// xóa thất bại
			response.sendRedirect(request.getContextPath() + "/admin/news?err=2");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

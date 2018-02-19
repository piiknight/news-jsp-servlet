package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    public AdminEditCatController() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		
		Category category = categoryDAO.getItem(id);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		request.setAttribute("category", category);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/cat/edit.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		
		Category category = categoryDAO.getItem(id);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=3");
		}
		String name = request.getParameter("name");
		
		// validate
		if (name.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/cat/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		category.setName(name);
		
		if(categoryDAO.editItem(category) > 0) {
			// sửa thành công
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=3");
			return;
		} else {
			// sửa thất bại
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/cat/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
	}

}

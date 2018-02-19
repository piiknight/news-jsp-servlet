package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import util.AuthUtil;


public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    public AdminDelCatController() {
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
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=2");
		}
		if (categoryDAO.delItem(id) > 0	) {
			// xóa thành công
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=2");
			return;
		} else {
			// xóa thất bại
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=2");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

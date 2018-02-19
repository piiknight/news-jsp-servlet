package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public AdminIndexUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int sumUser = userDAO.countUser();
		int sumPage = (int) Math.ceil((float) sumUser / Define.ROW_COUNT_ADMIN_USER);
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/users?error=3");
				return;
			}
		}
		int offset = (currentPage - 1) * Define.ROW_COUNT_ADMIN_USER;
		
		ArrayList<User> users = userDAO.getItemsPagination(offset);
		request.setAttribute("users", users);
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

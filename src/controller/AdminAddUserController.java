package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringLibrary;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO; 
	
    public AdminAddUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/add.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = StringLibrary.md5(request.getParameter("password"));
		String fullname = request.getParameter("fullname");
		
		// validate
		if (username.isEmpty() || password.isEmpty() || fullname.isEmpty() ) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		User user = new User(0, username, password, fullname);
		if (userDAO.getItemByUsername(username) != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/add.jsp?err=1");
			rd.forward(request, response);
			return;
		} else {
			if (userDAO.addItem(user) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/users?err=1");
				return;
			}
		}
	}

}

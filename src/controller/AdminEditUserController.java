package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringLibrary;


public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public AdminEditUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=1");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if (("admin".equals(userDAO.getItem(userLogin.getId()).getUsername())) || (id == userLogin.getId())) {
			User user = userDAO.getItem(id);
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/admin/users?err=2");
				return;
			}
			request.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/edit.jsp");
			rd.forward(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/users?error=1");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		
		// validate
		if (fullname.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/user/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=1");
			return;
		}
		
		if ("".equals(password)) {
			password = userDAO.getItem(id).getPassword();
		} else {
			password = StringLibrary.md5(password);
		}
		
		User user = new User(id, "", password, fullname);
		if (userDAO.editItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=5");
			return;
		}
		
	}

}
